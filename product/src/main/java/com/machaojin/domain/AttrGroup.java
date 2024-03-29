package com.machaojin.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.machaojin.vo.AttrVo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * 属性分组对象 pms_attr_group
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Alias("AttrGroup")
@TableName("pms_attr_group")
public class AttrGroup implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 分组id */
    private Long attrGroupId;

    /** 组名 */
    @Excel(name = "组名")
    private String attrGroupName;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 描述 */
    @Excel(name = "描述")
    private String descript;

    /** 组图标 */
    @Excel(name = "组图标")
    private String icon;

    /** 所属分类id */
    @Excel(name = "所属分类id")
    private Long catelogId;

    @Excel(name = "所属分类id的所有路径")
    @TableField(exist = false)
    private List<Long> catelogPath;

    @Excel(name = "分组下的所有基本属性")
    @TableField(exist = false)
    private List<AttrVo> attrs;

    public List<AttrVo> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<AttrVo> attrs) {
        this.attrs = attrs;
    }

    public List<Long> getCatelogPath() {
        return catelogPath;
    }

    public void setCatelogPath(List<Long> catelogPath) {
        this.catelogPath = catelogPath;
    }

    public void setAttrGroupId(Long attrGroupId)
    {
        this.attrGroupId = attrGroupId;
    }

    public Long getAttrGroupId() 
    {
        return attrGroupId;
    }
    public void setAttrGroupName(String attrGroupName) 
    {
        this.attrGroupName = attrGroupName;
    }

    public String getAttrGroupName() 
    {
        return attrGroupName;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setDescript(String descript) 
    {
        this.descript = descript;
    }

    public String getDescript() 
    {
        return descript;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setCatelogId(Long catelogId) 
    {
        this.catelogId = catelogId;
    }

    public Long getCatelogId() 
    {
        return catelogId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("attrGroupId", getAttrGroupId())
            .append("attrGroupName", getAttrGroupName())
            .append("sort", getSort())
            .append("descript", getDescript())
            .append("icon", getIcon())
            .append("catelogId", getCatelogId())
            .toString();
    }
}
