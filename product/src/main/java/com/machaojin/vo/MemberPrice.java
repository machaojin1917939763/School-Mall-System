/**
  * Copyright 2019 bejson.com 
  */
package com.machaojin.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Auto-generated: 2019-11-26 10:50:34
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class MemberPrice implements Serializable {

    private Long id;
    private String name;
    private BigDecimal price;

}