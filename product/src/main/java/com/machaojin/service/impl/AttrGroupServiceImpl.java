package com.machaojin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.AttrGroupMapper;
import com.machaojin.domain.AttrGroup;
import com.machaojin.service.IAttrGroupService;

/**
 * 属性分组Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service
public class AttrGroupServiceImpl implements IAttrGroupService 
{
    @Autowired
    private AttrGroupMapper attrGroupMapper;

    /**
     * 查询属性分组
     * 
     * @param attrGroupId 属性分组主键
     * @return 属性分组
     */
    @Override
    public AttrGroup selectAttrGroupByAttrGroupId(Long attrGroupId)
    {
        return attrGroupMapper.selectAttrGroupByAttrGroupId(attrGroupId);
    }

    /**
     * 查询属性分组列表
     * 
     * @param attrGroup 属性分组
     * @return 属性分组
     */
    @Override
    public List<AttrGroup> selectAttrGroupList(AttrGroup attrGroup)
    {
        return attrGroupMapper.selectAttrGroupList(attrGroup);
    }

    /**
     * 新增属性分组
     * 
     * @param attrGroup 属性分组
     * @return 结果
     */
    @Override
    public int insertAttrGroup(AttrGroup attrGroup)
    {
        return attrGroupMapper.insertAttrGroup(attrGroup);
    }

    /**
     * 修改属性分组
     * 
     * @param attrGroup 属性分组
     * @return 结果
     */
    @Override
    public int updateAttrGroup(AttrGroup attrGroup)
    {
        return attrGroupMapper.updateAttrGroup(attrGroup);
    }

    /**
     * 批量删除属性分组
     * 
     * @param attrGroupIds 需要删除的属性分组主键
     * @return 结果
     */
    @Override
    public int deleteAttrGroupByAttrGroupIds(Long[] attrGroupIds)
    {
        return attrGroupMapper.deleteAttrGroupByAttrGroupIds(attrGroupIds);
    }

    /**
     * 删除属性分组信息
     * 
     * @param attrGroupId 属性分组主键
     * @return 结果
     */
    @Override
    public int deleteAttrGroupByAttrGroupId(Long attrGroupId)
    {
        return attrGroupMapper.deleteAttrGroupByAttrGroupId(attrGroupId);
    }
}
