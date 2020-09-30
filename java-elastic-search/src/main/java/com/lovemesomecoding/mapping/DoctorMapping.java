package com.lovemesomecoding.mapping;

import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovemesomecoding.index.Index;
import com.lovemesomecoding.index.doctor.DoctorIndex;
import com.lovemesomecoding.utils.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DoctorMapping {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @PostConstruct
    public void init() {
        // loadData();
    }

    public void loadData() {
        log.info("setUpIndex");
        String indexName = Index.DOCTORS.name().toLowerCase();
        String utIndex = Index.DOCTOR_UT.name().toLowerCase();
        try {

            CountRequest countRequest = new CountRequest();
            SearchSourceBuilder countSourceBuilder = new SearchSourceBuilder();
            countSourceBuilder.query(QueryBuilders.termQuery("addresses.state.keyword", "UT"));
            countRequest.source(countSourceBuilder);

            CountResponse countResponse = restHighLevelClient.count(countRequest, RequestOptions.DEFAULT);

            long count = countResponse.getCount();

            log.info("count={}", count);

            /**
             * Scroll
             */
            final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
            SearchRequest searchRequest = new SearchRequest(indexName);
            searchRequest.allowPartialSearchResults(true);
            searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());
            searchRequest.scroll(scroll);

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(1000);
            searchSourceBuilder.query(QueryBuilders.termQuery("addresses.state.keyword", "UT"));
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            log.info("search got response from elastic!, totalHits={}, maxScore={}, hitLength={}", searchResponse.getHits().getTotalHits().value, searchResponse.getHits().getMaxScore(),
                    searchResponse.getHits().getHits().length);

            Iterator<SearchHit> it = searchResponse.getHits().iterator();
            while (it.hasNext()) {
                SearchHit searchHit = it.next();

                try {
                    // log.info(searchHit.getSourceAsString());
                    DoctorIndex doctorIndex = ObjectUtils.getObjectMapper().readValue(searchHit.getSourceAsString(), DoctorIndex.class);
                    // log.info("doctorIndex={}", ObjectUtils.toJson(doctorIndex));

                    // ObjectUtils.getObjectMapper().writeValue(new FileOutputStream("output-2.json", true),
                    // doctorIndex);

                    IndexRequest request = new IndexRequest(utIndex);
                    request.id(doctorIndex.getNpi());
                    request.source(searchHit.getSourceAsString(), XContentType.JSON);
                    IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            String scrollId = searchResponse.getScrollId();
            SearchHit[] searchHits = searchResponse.getHits().getHits();

            while (searchHits != null && searchHits.length > 0) {

                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
                scrollRequest.scroll(scroll);
                searchResponse = restHighLevelClient.scroll(scrollRequest, RequestOptions.DEFAULT);

                log.info("search got response from elastic!, totalHits={}, maxScore={}, hitLength={}", searchResponse.getHits().getTotalHits().value, searchResponse.getHits().getMaxScore(),
                        searchResponse.getHits().getHits().length);

                it = searchResponse.getHits().iterator();
                while (it.hasNext()) {
                    SearchHit searchHit = it.next();

                    try {
                        // log.info(searchHit.getSourceAsString());
                        DoctorIndex doctorIndex = ObjectUtils.getObjectMapper().readValue(searchHit.getSourceAsString(), DoctorIndex.class);
                        // log.info("doctorIndex={}", ObjectUtils.toJson(doctorIndex));

                        // ObjectUtils.getObjectMapper().writeValue(new FileOutputStream("output-2.json", true),
                        // doctorIndex);

                        IndexRequest request = new IndexRequest(utIndex);
                        request.id(doctorIndex.getNpi());
                        request.source(searchHit.getSourceAsString(), XContentType.JSON);
                        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                scrollId = searchResponse.getScrollId();
                searchHits = searchResponse.getHits().getHits();
            }

            ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
            clearScrollRequest.addScrollId(scrollId);
            ClearScrollResponse clearScrollResponse = restHighLevelClient.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
            boolean succeeded = clearScrollResponse.isSucceeded();

            // ObjectUtils.getObjectMapper().writeValue(new FileOutputStream("doctor-ut.json", true), doctors);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        log.info("setUpIndex done!");
    }

}
