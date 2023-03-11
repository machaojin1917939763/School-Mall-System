package com.machaojin.domain;

import com.machaojin.valid.AddGroups;
import com.machaojin.valid.ListValue;
import com.machaojin.valid.UpdateGroups;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 品牌对象 pms_brand
 * 
 * @author machaojin
 * @date 2022-10-05
 * JSR303：
 *      分组校验：
 *         在添加分组校验后，没有添加分组校验的校验不会生效，同样的，在没有添加分组校验的不会校验有分组校验的信息
 *
 */
@Alias("Brand")
public class Brand implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 品牌id */
    @NotNull(message = "修改的时候品牌ID不能为空",groups = {UpdateGroups.class})
    @Null(message = "新增的时候品牌Id必须为空",groups = {AddGroups.class})
    private Long brandId;

    /** 品牌名 */
    @Excel(name = "品牌名")
    @NotBlank(message = "商品品牌名不合法",groups = {UpdateGroups.class,AddGroups.class})
    private String name;

    /** 品牌logo地址 */
    @Excel(name = "品牌logo地址")
    @URL(message = "Logo地址不合法",groups = {UpdateGroups.class,AddGroups.class})
    @NotBlank(message = "Logo地址不能为空",groups = AddGroups.class)
    private String logo;

    /** 介绍 */
    @Excel(name = "介绍")
    private String descript;

    /** 显示状态[0-不显示；1-显示] */
    @Excel(name = "显示状态[0-不显示；1-显示]")
    @ListValue(values={0,1},groups = {AddGroups.class})
    private Integer showStatus;

    /** 检索首字母 */
    @Excel(name = "检索首字母")
    @NotNull(message = "检索首字母不能为空",groups = {UpdateGroups.class,AddGroups.class})
    @Pattern(regexp = "^[a-zA-Z]$",message = "检索首字母非法",groups = {UpdateGroups.class,AddGroups.class})
    private String firstLetter;

    /** 排序 */
    @Excel(name = "排序")
    @NotNull(message = "排序不能为空",groups = {UpdateGroups.class,AddGroups.class})
    @Min(value = 0,message = "排序数字必须大于0",groups = {UpdateGroups.class,AddGroups.class})
    private Long sort;

    /** 逻辑删除字段 */
    @Excel(name = "逻辑删除字段")
    private Long isDelete;


    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }

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
