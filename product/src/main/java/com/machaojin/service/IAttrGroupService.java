package com.machaojin.service;

import java.util.List;
import com.machaojin.domain.AttrGroup;

/**
 * 属性分组Service接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */

public interface IAttrGroupService 
{
    /**
     * 查询属性分组
     * 
     * @param attrGroupId 属性分组主键
     * @return 属性分组
     */
    public AttrGroup selectAttrGroupByAttrGroupId(Long attrGroupId);

    /**
     * 查询属性分组列表
     * 
     * @param attrGroup 属性分组
     * @return 属性分组集合
     */
    public List<AttrGroup> selectAttrGroupList(AttrGroup attrGroup);

    /**
     * 新增属性分组
     * 
     * @param attrGroup 属性分组
     * @return 结果
     */
    public int insertAttrGroup(AttrGroup attrGroup);

    /**
     * 修改属性分组
     * 
     * @param attrGroup 属性分组
     * @return 结果
     */
    public int updateAttrGroup(AttrGroup attrGroup);

    /**
     * 批量删除属性分组
     * 
     * @param attrGroupIds 需要删除的属性分组主键集合
     * @return 结果
     */
    public int deleteAttrGroupByAttrGroupIds(Long[] attrGroupIds);

    /**
     * 删除属性分组信息
     * 
     * @param attrGroupId 属性分组主键
     * @return 结果
     */
    public int deleteAttrGroupByAttrGroupId(Long attrGroupId);
}
