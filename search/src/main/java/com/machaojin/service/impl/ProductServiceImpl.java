package com.machaojin.service.impl;


import com.alibaba.fastjson.JSON;
import com.machaojin.config.ElasticSearchConfig;
import com.machaojin.dto.es.SpuUpDto;
import com.machaojin.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RestHighLevelClient client;
    /**
     * 保存商品信息--上架
     * @param spuUpDtos 商品信息
     * @return 保存结果
     */
    @Override
    public boolean saveProduct(List<SpuUpDto> spuUpDtos) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (SpuUpDto spu : spuUpDtos) {
            IndexRequest indexRequest = new IndexRequest("product");
            indexRequest.id(spu.getSkuId().toString());
            String jsonString = JSON.toJSONString(spu);
            indexRequest.source(jsonString, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulk = client.bulk(bulkRequest, ElasticSearchConfig.COMMON_OPTIONS);
        BulkItemResponse[] items = bulk.getItems();
        List<String> list = Arrays.stream(items).map(BulkItemResponse::getId).collect(Collectors.toList());
        log.error("上架失败{}",list);
        return bulk.hasFailures();
    }
}
