package com.lovemesomecoding;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
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
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lovemesomecoding.index.Index;
import com.lovemesomecoding.index.doctor.DoctorIndex;
import com.lovemesomecoding.utils.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class DoctorDataLoader {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
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

            SearchRequest searchRequest = new SearchRequest(indexName);
            searchRequest.allowPartialSearchResults(true);
            searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.filter(QueryBuilders.termQuery("addresses.state.keyword", "UT"));

            int from = 0;
            int size = 1000;
            List<DoctorIndex> doctors = new ArrayList<>();

            while (size < count) {

                searchSourceBuilder.from(from);
                searchSourceBuilder.size(size);
                searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

                searchSourceBuilder.query(boolQueryBuilder);

                searchRequest.source(searchSourceBuilder);
                SearchResponse searchResponse = null;

                try {
                    searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

                } catch (Exception e) {
                    log.warn(e.getLocalizedMessage());
                }

                if (searchResponse != null) {

                    log.info("search got response from elastic!, totalHits={}, maxScore={}, hitLength={}", searchResponse.getHits().getTotalHits().value, searchResponse.getHits().getMaxScore(),
                            searchResponse.getHits().getHits().length);

                    Iterator<SearchHit> it = searchResponse.getHits().iterator();
                    while (it.hasNext()) {
                        SearchHit searchHit = it.next();

                        try {
                            // log.info(searchHit.getSourceAsString());
                            DoctorIndex doctorIndex = ObjectUtils.getObjectMapper().readValue(searchHit.getSourceAsString(), DoctorIndex.class);
                            // log.info("doctorIndex={}", ObjectUtils.toJson(doctorIndex));
                            doctors.add(doctorIndex);

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
                    from++;
                    from = from + size;

                    log.info("from={},size={}", from, size);
                } else {
                    break;
                }

                // log.info("doctors={}", ObjectUtils.toJson(doctors));
            }
            log.info("doctors.size={}", doctors.size());
            ObjectUtils.getObjectMapper().writeValue(new FileOutputStream("doctor-ut.json", true), doctors);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
