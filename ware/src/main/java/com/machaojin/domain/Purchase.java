package com.machaojin.domain;

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
 * 采购信息对象 wms_purchase
 * 
 * @author machaojin
 * @date 2022-10-06
 */
@Alias("Purchase")
@TableName("wms_purchase")
@Data
public class Purchase
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long assigneeId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String assigneeName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String phone;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer priority;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer status;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long wareId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal amount;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
