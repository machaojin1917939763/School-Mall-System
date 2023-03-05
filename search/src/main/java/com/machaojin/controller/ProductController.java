package com.machaojin.controller;


import com.machaojin.dto.es.SpuUpDto;
import com.machaojin.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/search")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/up")
    public boolean productUp(@RequestBody List<SpuUpDto> spuUpDtos) throws IOException {
       return productService.saveProduct(spuUpDtos);
    }

}
