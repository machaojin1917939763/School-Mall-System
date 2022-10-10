package com.machaojin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌对象 pms_brand
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Alias("Brand")
public class Brand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 品牌id */
    private Long brandId;

    /** 品牌名 */
    @Excel(name = "品牌名")
    @NotBlank(message = "商品品牌名不合法")
    private String name;

    /** 品牌logo地址 */
    @Excel(name = "品牌logo地址")
    @URL(message = "Logo地址不合法")
    @NotNull
    private String logo;

    /** 介绍 */
    @Excel(name = "介绍")
    private String descript;

    /** 显示状态[0-不显示；1-显示] */
    @Excel(name = "显示状态[0-不显示；1-显示]")
    private Integer showStatus;

    /** 检索首字母 */
    @Excel(name = "检索首字母")
    @NotNull
    @Pattern(regexp = "/^[a-zA-Z]$/",message = "检索首字母非法")
    private String firstLetter;

    /** 排序 */
    @Excel(name = "排序")
    @NotNull
    @Min(value = 0,message = "排序数字必须大于0")
    private Long sort;

    public void setBrandId(Long brandId) 
    {
        this.brandId = brandId;
    }

    public Long getBrandId() 
    {
        return brandId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setLogo(String logo) 
    {
        this.logo = logo;
    }

    public String getLogo() 
    {
        return logo;
    }
    public void setDescript(String descript) 
    {
        this.descript = descript;
    }

    public String getDescript() 
    {
        return descript;
    }
    public void setShowStatus(Integer showStatus) 
    {
        this.showStatus = showStatus;
    }

    public Integer getShowStatus() 
    {
        return showStatus;
    }
    public void setFirstLetter(String firstLetter) 
    {
        this.firstLetter = firstLetter;
    }

    public String getFirstLetter() 
    {
        return firstLetter;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("brandId", getBrandId())
            .append("name", getName())
            .append("logo", getLogo())
            .append("descript", getDescript())
            .append("showStatus", getShowStatus())
            .append("firstLetter", getFirstLetter())
            .append("sort", getSort())
            .toString();
    }
}
