package com.machaojin.feign;

import com.machaojin.dto.es.SpuUpDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

@FeignClient(value = "search")
public interface SearchFeignClients {

    @PostMapping("/search/product/up")
    public boolean productUp(@RequestBody List<SpuUpDto> spuUpDtos) throws IOException;
}
