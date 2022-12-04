package com.machaojin.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.machaojin.domain.AttrGroup;
import com.machaojin.vo.AttrRelationVo;
import org.apache.ibatis.annotations.Param;

/**
 * 属性分组Mapper接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@org.apache.ibatis.annotations.Mapper
public interface AttrGroupMapper extends BaseMapper<AttrGroup>
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

    /**
     * 根据条件查询属性分组
     * @param params 条件
     * @param catalogId 分组id
     * @return 属性分组
     */
    List<AttrGroup> selectAttrGroupListForList(Map<String, Object> params, @Param("id") String catalogId);


}
