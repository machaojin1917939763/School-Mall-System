package com.machaojin.mapper;

import java.util.List;
import com.machaojin.domain.AttrGroup;

/**
 * 属性分组Mapper接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@org.apache.ibatis.annotations.Mapper
public interface AttrGroupMapper 
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
     * 删除属性分组
     * 
     * @param attrGroupId 属性分组主键
     * @return 结果
     */
    public int deleteAttrGroupByAttrGroupId(Long attrGroupId);

    /**
     * 批量删除属性分组
     * 
     * @param attrGroupIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAttrGroupByAttrGroupIds(Long[] attrGroupIds);
}
