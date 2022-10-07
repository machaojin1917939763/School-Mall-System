package com.machaojin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.AttrMapper;
import com.machaojin.domain.Attr;
import com.machaojin.service.IAttrService;

/**
 * 商品属性Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service
public class AttrServiceImpl implements IAttrService 
{
    @Autowired
    private AttrMapper attrMapper;

    /**
     * 查询商品属性
     * 
     * @param attrId 商品属性主键
     * @return 商品属性
     */
    @Override
    public Attr selectAttrByAttrId(Long attrId)
    {
        return attrMapper.selectAttrByAttrId(attrId);
    }

    /**
     * 查询商品属性列表
     * 
     * @param attr 商品属性
     * @return 商品属性
     */
    @Override
    public List<Attr> selectAttrList(Attr attr)
    {
        return attrMapper.selectAttrList(attr);
    }

    /**
     * 新增商品属性
     * 
     * @param attr 商品属性
     * @return 结果
     */
    @Override
    public int insertAttr(Attr attr)
    {
        return attrMapper.insertAttr(attr);
    }

    /**
     * 修改商品属性
     * 
     * @param attr 商品属性
     * @return 结果
     */
    @Override
    public int updateAttr(Attr attr)
    {
        return attrMapper.updateAttr(attr);
    }

    /**
     * 批量删除商品属性
     * 
     * @param attrIds 需要删除的商品属性主键
     * @return 结果
     */
    @Override
    public int deleteAttrByAttrIds(Long[] attrIds)
    {
        return attrMapper.deleteAttrByAttrIds(attrIds);
    }

    /**
     * 删除商品属性信息
     * 
     * @param attrId 商品属性主键
     * @return 结果
     */
    @Override
    public int deleteAttrByAttrId(Long attrId)
    {
        return attrMapper.deleteAttrByAttrId(attrId);
    }
}
