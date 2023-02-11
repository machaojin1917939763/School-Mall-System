package com.machaojin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.machaojin.domain.AttrAttrgroupRelation;
import com.machaojin.vo.AttrRelationVo;
import org.apache.ibatis.annotations.Param;

/**
 * 属性&属性分组关联Mapper接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@org.apache.ibatis.annotations.Mapper
public interface AttrAttrgroupRelationMapper extends BaseMapper<AttrAttrgroupRelation>
{
    /**
     * 查询属性&属性分组关联
     * 
     * @param id 属性&属性分组关联主键
     * @return 属性&属性分组关联
     */
    public AttrAttrgroupRelation selectAttrAttrgroupRelationById(Long id);

    /**
     * 查询属性&属性分组关联列表
     * 
     * @param attrAttrgroupRelation 属性&属性分组关联
     * @return 属性&属性分组关联集合
     */
    public List<AttrAttrgroupRelation> selectAttrAttrgroupRelationList(AttrAttrgroupRelation attrAttrgroupRelation);

    /**
     * 新增属性&属性分组关联
     * 
     * @param attrAttrgroupRelation 属性&属性分组关联
     * @return 结果
     */
    public int insertAttrAttrgroupRelation(@Param("attrAttrgroupRelation") List<AttrAttrgroupRelation> attrAttrgroupRelation);

    /**
     * 修改属性&属性分组关联
     * 
     * @param attrAttrgroupRelation 属性&属性分组关联
     * @return 结果
     */
    public int updateAttrAttrgroupRelation(AttrAttrgroupRelation attrAttrgroupRelation);

    /**
     * 删除属性&属性分组关联
     * 
     * @param id 属性&属性分组关联主键
     * @return 结果
     */
    public int deleteAttrAttrgroupRelationById(Long id);

    /**
     * 批量删除属性&属性分组关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAttrAttrgroupRelationByIds(Long[] ids);

    /**
     * 根据属性的分组信息删除属性关系表里面的数据
     * @return
     */
    int deleteFor(@Param("collect") List<AttrRelationVo> collect);
}
