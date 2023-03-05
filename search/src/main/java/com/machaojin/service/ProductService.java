package com.machaojin.service;

import com.machaojin.dto.es.SpuUpDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductService {

    /**
     * 保存商品信息--上架
     * @param spuUpDtos 商品信息
     * @return 保存结果
     */
    boolean saveProduct(List<SpuUpDto> spuUpDtos) throws IOException;
}
