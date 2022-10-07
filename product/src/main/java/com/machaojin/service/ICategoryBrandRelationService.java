package com.machaojin.service;

import java.util.List;
import com.machaojin.domain.CategoryBrandRelation;

/**
 * 品牌分类关联Service接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */

public interface ICategoryBrandRelationService 
{
    /**
     * 查询品牌分类关联
     * 
     * @param id 品牌分类关联主键
     * @return 品牌分类关联
     */
    public CategoryBrandRelation selectCategoryBrandRelationById(Long id);

    /**
     * 查询品牌分类关联列表
     * 
     * @param categoryBrandRelation 品牌分类关联
     * @return 品牌分类关联集合
     */
    public List<CategoryBrandRelation> selectCategoryBrandRelationList(CategoryBrandRelation categoryBrandRelation);

    /**
     * 新增品牌分类关联
     * 
     * @param categoryBrandRelation 品牌分类关联
     * @return 结果
     */
    public int insertCategoryBrandRelation(CategoryBrandRelation categoryBrandRelation);

    /**
     * 修改品牌分类关联
     * 
     * @param categoryBrandRelation 品牌分类关联
     * @return 结果
     */
    public int updateCategoryBrandRelation(CategoryBrandRelation categoryBrandRelation);

    /**
     * 批量删除品牌分类关联
     * 
     * @param ids 需要删除的品牌分类关联主键集合
     * @return 结果
     */
    public int deleteCategoryBrandRelationByIds(Long[] ids);

    /**
     * 删除品牌分类关联信息
     * 
     * @param id 品牌分类关联主键
     * @return 结果
     */
    public int deleteCategoryBrandRelationById(Long id);
}
