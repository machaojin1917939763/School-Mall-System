package com.machaojin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.AttrAttrgroupRelationMapper;
import com.machaojin.domain.AttrAttrgroupRelation;
import com.machaojin.service.IAttrAttrgroupRelationService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 属性&属性分组关联Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */

@Service
public class AttrAttrgroupRelationServiceImpl implements IAttrAttrgroupRelationService
{
    @Autowired
    private AttrAttrgroupRelationMapper attrAttrgroupRelationMapper;

    /**
     * 查询属性&属性分组关联
     * 
     * @param id 属性&属性分组关联主键
     * @return 属性&属性分组关联
     */
    @Override
    public AttrAttrgroupRelation selectAttrAttrgroupRelationById(Long id)
    {
        return attrAttrgroupRelationMapper.selectAttrAttrgroupRelationById(id);
    }

    /**
     * 查询属性&属性分组关联列表
     * 
     * @param attrAttrgroupRelation 属性&属性分组关联
     * @return 属性&属性分组关联
     */
    @Override
    public List<AttrAttrgroupRelation> selectAttrAttrgroupRelationList(AttrAttrgroupRelation attrAttrgroupRelation)
    {
        return attrAttrgroupRelationMapper.selectAttrAttrgroupRelationList(attrAttrgroupRelation);
    }

    /**
     * 新增属性&属性分组关联
     * 
     * @param attrAttrgroupRelation 属性&属性分组关联
     * @return 结果
     */
    @Override
    public int insertAttrAttrgroupRelation(List<AttrAttrgroupRelation> attrAttrgroupRelation)
    {
        return attrAttrgroupRelationMapper.insertAttrAttrgroupRelation(attrAttrgroupRelation);
    }

    /**
     * 修改属性&属性分组关联
     * 
     * @param attrAttrgroupRelation 属性&属性分组关联
     * @return 结果
     */
    @Override
    public int updateAttrAttrgroupRelation(AttrAttrgroupRelation attrAttrgroupRelation)
    {
        return attrAttrgroupRelationMapper.updateAttrAttrgroupRelation(attrAttrgroupRelation);
    }

    /**
     * 批量删除属性&属性分组关联
     * 
     * @param ids 需要删除的属性&属性分组关联主键
     * @return 结果
     */
    @Override
    public int deleteAttrAttrgroupRelationByIds(Long[] ids)
    {
        return attrAttrgroupRelationMapper.deleteAttrAttrgroupRelationByIds(ids);
    }

    /**
     * 删除属性&属性分组关联信息
     * 
     * @param id 属性&属性分组关联主键
     * @return 结果
     */
    @Override
    public int deleteAttrAttrgroupRelationById(Long id)
    {
        return attrAttrgroupRelationMapper.deleteAttrAttrgroupRelationById(id);
    }
}
