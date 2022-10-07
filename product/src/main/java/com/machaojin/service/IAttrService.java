package com.machaojin.service;

import java.util.List;
import com.machaojin.domain.Attr;

/**
 * 商品属性Service接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */

public interface IAttrService 
{
    /**
     * 查询商品属性
     * 
     * @param attrId 商品属性主键
     * @return 商品属性
     */
    public Attr selectAttrByAttrId(Long attrId);

    /**
     * 查询商品属性列表
     * 
     * @param attr 商品属性
     * @return 商品属性集合
     */
    public List<Attr> selectAttrList(Attr attr);

    /**
     * 新增商品属性
     * 
     * @param attr 商品属性
     * @return 结果
     */
    public int insertAttr(Attr attr);

    /**
     * 修改商品属性
     * 
     * @param attr 商品属性
     * @return 结果
     */
    public int updateAttr(Attr attr);

    /**
     * 批量删除商品属性
     * 
     * @param attrIds 需要删除的商品属性主键集合
     * @return 结果
     */
    public int deleteAttrByAttrIds(Long[] attrIds);

    /**
     * 删除商品属性信息
     * 
     * @param attrId 商品属性主键
     * @return 结果
     */
    public int deleteAttrByAttrId(Long attrId);
}
