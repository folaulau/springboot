package com.lovemesomecoding;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
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
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
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
public class DoctorDocAPI {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void getDoctorByNPI() {
        String indexName = Index.DOCTOR_UT.name().toLowerCase();

        String npi = "1013143536";
        GetRequest getRequest = new GetRequest(indexName, npi);

        try {
            GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            log.info(getResponse.getSourceAsString());
        } catch (Exception e) {
            log.warn(e.getLocalizedMessage());
        }
    }

    @Test
    public void getMultipleDoctorsByNPIs() {
        String utahDoctorIndex = Index.DOCTOR_UT.name().toLowerCase();
        String doctorsIndex = Index.DOCTORS.name().toLowerCase();

        String npi1 = "1013143536";
        String npi2 = "1073883070";

        GetRequest getRequest = new GetRequest(utahDoctorIndex, npi1);
        MultiGetRequest request = new MultiGetRequest();
        request.add(new MultiGetRequest.Item(utahDoctorIndex, npi1));
        request.add(new MultiGetRequest.Item(doctorsIndex, npi2));

        try {
            MultiGetResponse response = restHighLevelClient.mget(request, RequestOptions.DEFAULT);

            // utah doctor
            MultiGetItemResponse utahDoctor = response.getResponses()[0];
            log.info(utahDoctor.getResponse().getSourceAsString());

            MultiGetItemResponse doctor = response.getResponses()[1];
            log.info(doctor.getResponse().getSourceAsString());
        } catch (Exception e) {
            log.warn(e.getLocalizedMessage());
        }
    }

    @Test
    public void updateDoctor() {
        String indexName = Index.DOCTOR_UT.name().toLowerCase();
        String npi = "1013143536";

        UpdateRequest request = new UpdateRequest(indexName, npi);
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("firstName", "Folau");

        request.doc(jsonMap, XContentType.JSON);

        try {
            UpdateResponse updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
            log.info(updateResponse.getGetResult().sourceAsString());
        } catch (Exception e) {
            log.warn(e.getLocalizedMessage());
        }
    }

    @Test
    public void batchUpdateDoctors() {
        String indexName = Index.DOCTOR_UT.name().toLowerCase();

        UpdateByQueryRequest request = new UpdateByQueryRequest(indexName);
        request.setQuery(new TermQueryBuilder("firstName", "new_name1"));
        request.setScript(new Script(ScriptType.INLINE, "painless", "if (ctx._source.firstName == 'new_name1') {ctx._source.firstName='Kinga';}", Collections.emptyMap()));

        try {
            BulkByScrollResponse bulkResponse = restHighLevelClient.updateByQuery(request, RequestOptions.DEFAULT);
            log.info("updated={}", bulkResponse.getStatus().getUpdated());
        } catch (Exception e) {
            log.warn(e.getLocalizedMessage());
        }
    }

    @Test
    public void deleteDoctor() {
        String indexName = Index.DOCTOR_UT.name().toLowerCase();
        String npi = "1013143536";

        DeleteRequest request = new DeleteRequest(indexName, npi);
        try {
            DeleteResponse deleteResponse = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
            log.info(deleteResponse.getIndex());
        } catch (Exception e) {
            log.warn(e.getLocalizedMessage());
        }
    }

}
