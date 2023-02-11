package com.machaojin.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.machaojin.domain.Attr;
import com.machaojin.vo.AttrRelationVo;
import com.machaojin.vo.AttrReqVo;
import com.machaojin.vo.AttrVo;

/**
 * 商品属性Service接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */

public interface IAttrService extends IService<Attr>
{
    /**
     * 查询商品属性
     * 
     * @param attrId 商品属性主键
     * @return 商品属性
     */
    public AttrReqVo selectAttrByAttrId(Long attrId);

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
    public int updateAttr(AttrVo attr);

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

    /**
     * 保存商品的属性以及更新 ID
     * @param attr 传递过来的属性
     */
    int insertAttrVo(AttrVo attr);

    /**
     * 查询所有的属性
     * @param page 分页数据
     * @param params 条件
     * @return 返回分页数据
     *
     */
    Page<AttrReqVo> selectAttrList(Page<Attr> page, Map<String, String> params,String id,String type);


    /**
     * 根据分组 ID 查找关联的所有属性，规格参数
     * @param attrgroupId
     * @return
     */
    List<Attr> selectAttrListAll(Long attrgroupId);

}
