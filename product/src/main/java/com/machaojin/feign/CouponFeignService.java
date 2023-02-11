package com.machaojin.feign;

import com.machaojin.dto.SkuReductionTo;
import com.machaojin.dto.SpuBoundsDto;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "coupon")
public interface CouponFeignService {

    @PostMapping("/machaojin/bounds/save")
    AjaxResult saveBounds(@RequestBody SpuBoundsDto spuBoundsDto);

    @PostMapping("/machaojin/reduction/save/info")
    AjaxResult saveReduction(@RequestBody SkuReductionTo skuReductionTo);
}
