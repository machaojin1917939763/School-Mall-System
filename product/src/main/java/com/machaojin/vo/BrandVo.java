package com.machaojin.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BrandVo implements Serializable {

    /**
     * "brandId": 0,
     * "brandName": "string",
     */
    private Long brandId;
    private String  brandName;
}
