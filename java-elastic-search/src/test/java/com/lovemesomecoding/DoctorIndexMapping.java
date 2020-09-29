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
public class DoctorIndexMapping {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void setUpDoctorMapping() {
        log.info("setUpDoctorMapping");
        String indexName = Index.DOCTOR_UT.name().toLowerCase();

        try {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
            AcknowledgedResponse deleteIndexResponse = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);

        } catch (Exception e) {
            log.warn(e.getLocalizedMessage());
        }

        try {

            CreateIndexRequest request = new CreateIndexRequest(indexName);

            request.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 2));

            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            {
                builder.startObject("properties");
                {
                    builder.startObject("locations");
                    {
                        builder.field("type", "geo_point");
                    }
                    builder.endObject();

                    builder.startObject("addresses");
                    {
                        builder.field("type", "nested");
                    }
                    builder.endObject();

                    builder.startObject("specialities");
                    {
                        builder.field("type", "nested");
                    }
                    builder.endObject();

                }
                builder.endObject();
            }
            builder.endObject();
            request.mapping(builder);

            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

            log.info("{} mapping has been set up!", indexName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
