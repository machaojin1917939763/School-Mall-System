package com.machaojin;

import com.alibaba.fastjson.JSON;
import com.machaojin.config.ElasticSearchConfig;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    @Test
   public void contextLoads() {
        System.out.println(client);
    }

    @Test
    public void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
        indexRequest.source("username","machaojin");
        indexRequest.source("age",20);
        indexRequest.source("address","贵州省");
        IndexResponse index = client.index(indexRequest, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(index.toString());
    }

    @Test
    public void getData() throws IOException {
        //创建索引请求
        SearchRequest request = new SearchRequest();
        //指定查询索引
        request.indices("bank");
        //封装条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        request.source(searchSourceBuilder);

        SearchResponse search = client.search(request, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(searchSourceBuilder.toString());
        System.out.println("--------------");
        System.out.println(search.toString());
    }
    @Test
    public void  getDataByCondition() throws IOException {
        //创建索引请求
        SearchRequest request = new SearchRequest();
        //选择查询索引
        request.indices("bank");
        //构造查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        TermsAggregationBuilder state_terms = AggregationBuilders.terms("state_terms").field("state.keyword").size(10);
        AvgAggregationBuilder avg_balance = AggregationBuilders.avg("avg_balance").field("balance");
        TermsAggregationBuilder gender_terms = AggregationBuilders.terms("gender_terms").field("gender.keyword").size(10);
        //再次构造条件
        gender_terms.subAggregation(AggregationBuilders.avg("avg_balance_byGender").field("balance"));
        state_terms.subAggregation(avg_balance);
        state_terms.subAggregation(gender_terms);
        //将查询条件封装到索引请求中
        searchSourceBuilder.aggregation(state_terms);
        request.source(searchSourceBuilder);
        //打印查询条件
        System.out.println(searchSourceBuilder);
        //发送请求查询
        SearchResponse search = client.search(request, ElasticSearchConfig.COMMON_OPTIONS);
        //打印请求信息
        SearchHits hits = search.getHits();
        SearchHit[] hitsHits = hits.getHits();
        for (SearchHit hitsHit : hitsHits) {
            String sourceAsString = hitsHit.getSourceAsString();
            System.out.println(sourceAsString);
        }
        System.out.println("--------");
    }

}
