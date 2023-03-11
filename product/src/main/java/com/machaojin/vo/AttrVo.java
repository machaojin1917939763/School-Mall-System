package com.machaojin.vo;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author machaojin
 * @version 1.0
 * @time 2022/11/24 19:23 星期四
 */
@Alias("AttrVo")
public class AttrVo implements Serializable {
    /** 属性id */
    private Long attrId;

    /** 属性名 */
    @Excel(name = "属性名")
    private String attrName;

    /** 是否需要检索[0-不需要，1-需要] */
    @Excel(name = "是否需要检索[0-不需要，1-需要]")
    private Integer searchType;

    /** 属性图标 */
    @Excel(name = "属性图标")
    private String icon;

    /** 可选值列表[用逗号分隔] */
    @Excel(name = "可选值列表[用逗号分隔]")
    private String valueSelect;

    /** 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性] */
    @Excel(name = "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
    private Integer attrType;

    /** 启用状态[0 - 禁用，1 - 启用] */
    @Excel(name = "启用状态[0 - 禁用，1 - 启用]")
    private Long enable;

    /** 所属分类 */
    @Excel(name = "所属分类")
    private Long catelogId;

    /** 分组 ID */
    @Excel(name = "分组 ID")
    private String attrGroupId;

    /** 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整 */
    @Excel(name = "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
    private Integer showDesc;

    /** 值类型 */
    @Excel(name = "值类型")
    private String valueType;

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public Integer getShowDesc() {
        return showDesc;
    }

    public void setShowDesc(Integer showDesc) {
        this.showDesc = showDesc;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getValueSelect() {
        return valueSelect;
    }

    public void setValueSelect(String valueSelect) {
        this.valueSelect = valueSelect;
    }

    public Integer getAttrType() {
        return attrType;
    }

    public void setAttrType(Integer attrType) {
        this.attrType = attrType;
    }

    public Long getEnable() {
        return enable;
    }

    public void setEnable(Long enable) {
        this.enable = enable;
    }

    public Long getCatelogId() {
        return catelogId;
    }

    public void setCatelogId(Long catelogId) {
        this.catelogId = catelogId;
    }

    public String getAttrGroupId() {
        return attrGroupId;
    }

    public void setAttrGroupId(String attrGroupId) {
        this.attrGroupId = attrGroupId;
    }

    @Override
    public String toString() {
        return "AttrVo{" +
                "attrId=" + attrId +
                ", attrName='" + attrName + '\'' +
                ", searchType=" + searchType +
                ", icon='" + icon + '\'' +
                ", valueSelect='" + valueSelect + '\'' +
                ", attrType=" + attrType +
                ", enable=" + enable +
                ", catelogId=" + catelogId +
                ", attrGroupId='" + attrGroupId + '\'' +
                '}';
    }
}
