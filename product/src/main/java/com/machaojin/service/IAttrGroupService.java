package com.machaojin.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.machaojin.domain.Attr;
import com.machaojin.domain.AttrGroup;
import com.machaojin.domain.Category;
import com.machaojin.vo.AttrRelationVo;

/**
 * 属性分组Service接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */

public interface IAttrGroupService extends IService<AttrGroup>
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

    /**
     * 查询属性分组列表
     * @param params {
     *    page: 1,//当前页码
     *    limit: 10,//每页记录数
     *    sidx: 'id',//排序字段
     *    order: 'asc/desc',//排序方式
     *    key: '华为'//检索关键字
     * }
     * @param  catalogId id
     * @return 属性分组集合
     */
    Page<AttrGroup> selectAttrGroupListForList(Map<String, Object> params, String catalogId);

    /**
     * 根据条件删除属性和属性分组的关系
     * @param attrGroupIds
     * @return
     */
    int deleteFor(AttrRelationVo[] attrGroupIds);

    /**
     * 获取没有被属性分组所关联的属性关系
     * @param attrGroupId
     * @param params
     * @return
     */
    List<Attr> listFor(String attrGroupId, Map<Object, Object> params);

    /**
     * 根据分类id查询出所有的分组信息，然后再查出每个分组信息关联的所有属性
     * @param categoryId
     * @return
     */
    List<AttrGroup> listAllAttr(Long categoryId);
}
