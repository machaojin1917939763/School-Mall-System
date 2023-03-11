package com.machaojin.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * 商品三级分类对象 pms_category
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Alias("Category")
@TableName("pms_category")
public class Category implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 分类id */
    @TableId
    private Long catId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String name;

    /** 父分类id */
    @Excel(name = "父分类id")
    private Long parentCid;

    /** 层级 */
    @Excel(name = "层级")
    private Long catLevel;

    /** 是否显示[0-不显示，1显示] */
    @Excel(name = "是否显示[0-不显示，1显示]")
    @TableLogic(delval = "0",value = "show_status")
    private Integer showStatus;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 图标地址 */
    @Excel(name = "图标地址")
    private String icon;

    /** 计量单位 */
    @Excel(name = "计量单位")
    private String productUnit;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long productCount;

    /**
     * 该分类的子分类
     * JsonInclude(JsonInclude.Include.NON_EMPTY):不为空就返回，为空的时候在返回数据的时候就不带上他
     */
    @Excel(name = "该分类的子分类")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private List<Category> children;

    public void setCatId(Long catId) 
    {
        this.catId = catId;
    }

    public Long getCatId() 
    {
        return catId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setParentCid(Long parentCid) 
    {
        this.parentCid = parentCid;
    }

    public Long getParentCid() 
    {
        return parentCid;
    }
    public void setCatLevel(Long catLevel) 
    {
        this.catLevel = catLevel;
    }

    public Long getCatLevel() 
    {
        return catLevel;
    }
    public void setShowStatus(Integer showStatus) 
    {
        this.showStatus = showStatus;
    }

    public Integer getShowStatus() 
    {
        return showStatus;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setProductUnit(String productUnit) 
    {
        this.productUnit = productUnit;
    }

    public String getProductUnit() 
    {
        return productUnit;
    }
    public void setProductCount(Long productCount) 
    {
        this.productCount = productCount;
    }

    public Long getProductCount() 
    {
        return productCount;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("catId", getCatId())
            .append("name", getName())
            .append("parentCid", getParentCid())
            .append("catLevel", getCatLevel())
            .append("showStatus", getShowStatus())
            .append("sort", getSort())
            .append("icon", getIcon())
            .append("productUnit", getProductUnit())
            .append("productCount", getProductCount())
            .toString();
    }
}
