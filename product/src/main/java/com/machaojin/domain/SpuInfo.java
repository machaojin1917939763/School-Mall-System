package com.machaojin.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.ibatis.type.Alias;

/**
 * spu信息对象 pms_spu_info
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Alias("SpuInfo")
@TableName("pms_spu_info")
@Data
public class SpuInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    private Long id;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String spuName;

    /** 商品描述 */
    @Excel(name = "商品描述")
    private String spuDescription;

    /** 所属分类id */
    @Excel(name = "所属分类id")
    private Long catalogId;

    /** 品牌id */
    @Excel(name = "品牌id")
    private Long brandId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal weight;

    /** 上架状态[0 - 下架，1 - 上架] */
    @Excel(name = "上架状态[0 - 下架，1 - 上架]")
    private Integer publishStatus;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
