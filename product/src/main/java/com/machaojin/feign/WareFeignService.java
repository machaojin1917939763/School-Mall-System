package com.machaojin.feign;

import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "ware")
public interface WareFeignService {

    @PostMapping(value = "/machaojin/ware/sku/has/stock")
    public AjaxResult getHasStockInfos(@RequestBody List<Long> skuIds);
}
