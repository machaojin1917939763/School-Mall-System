package com.machaojin.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.machaojin.domain.SkuInfo;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
public class SkuInfoVo  {
    private Double min;
    private Double max;

    private Long skuId;


    private Long spuId;


    private String skuName;


    private String skuDesc;


    private Long catalogId;


    private Long brandId;


    private String skuDefaultImg;


    private String skuTitle;


    private String skuSubtitle;


    private BigDecimal price;


    private Long saleCount;
}
