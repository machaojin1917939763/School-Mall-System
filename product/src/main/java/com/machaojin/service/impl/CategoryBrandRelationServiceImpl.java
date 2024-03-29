package com.machaojin.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.machaojin.domain.Brand;
import com.machaojin.domain.Category;
import com.machaojin.domain.SpuInfoDesc;
import com.machaojin.mapper.BrandMapper;
import com.machaojin.mapper.CategoryMapper;
import com.machaojin.mapper.SpuInfoDescMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.CategoryBrandRelationMapper;
import com.machaojin.domain.CategoryBrandRelation;
import com.machaojin.service.ICategoryBrandRelationService;

/**
 * 品牌分类关联Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelation> implements ICategoryBrandRelationService
{
    @Autowired
    private CategoryBrandRelationMapper categoryBrandRelationMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询品牌分类关联
     * 
     * @param id 品牌分类关联主键
     * @return 品牌分类关联
     */
    @Override
    public CategoryBrandRelation selectCategoryBrandRelationById(Long id)
    {
        return categoryBrandRelationMapper.selectCategoryBrandRelationById(id);
    }

    /**
     * 查询品牌分类关联列表
     * 
     * @param categoryBrandRelation 品牌分类关联
     * @return 品牌分类关联
     */
    @Override
    public List<CategoryBrandRelation> selectCategoryBrandRelationList(CategoryBrandRelation categoryBrandRelation)
    {
        return categoryBrandRelationMapper.selectCategoryBrandRelationList(categoryBrandRelation);
    }

    /**
     * 新增品牌分类关联
     * 
     * @param categoryBrandRelation 品牌分类关联
     * @return 结果
     */
    @Override
    public int insertCategoryBrandRelation(CategoryBrandRelation categoryBrandRelation)
    {
        //先在category里面查询出
        Category category = categoryMapper.selectCategoryByCatId(categoryBrandRelation.getCatelogId());
        Brand brand = brandMapper.selectBrandByBrandId(categoryBrandRelation.getBrandId());
        categoryBrandRelation.setBrandName(brand.getName());
        categoryBrandRelation.setCatelogName(category.getName());
        return categoryBrandRelationMapper.insertCategoryBrandRelation(categoryBrandRelation);
    }

    /**
     * 修改品牌分类关联
     * 
     * @param categoryBrandRelation 品牌分类关联
     * @return 结果
     */
    @Override
    public int updateCategoryBrandRelation(CategoryBrandRelation categoryBrandRelation)
    {
        return categoryBrandRelationMapper.updateCategoryBrandRelation(categoryBrandRelation);
    }

    /**
     * 批量删除品牌分类关联
     * 
     * @param ids 需要删除的品牌分类关联主键
     * @return 结果
     */
    @Override
    public int deleteCategoryBrandRelationByIds(Long[] ids)
    {
        return categoryBrandRelationMapper.deleteCategoryBrandRelationByIds(ids);
    }

    /**
     * 删除品牌分类关联信息
     * 
     * @param id 品牌分类关联主键
     * @return 结果
     */
    @Override
    public int deleteCategoryBrandRelationById(Long id)
    {
        return categoryBrandRelationMapper.deleteCategoryBrandRelationById(id);
    }

    /**
     * 根据分类Id查询分类与品牌的关系
     * @param categoryId
     * @return
     */
    @Override
    public List<CategoryBrandRelation> selectCategoryBrandRelationByCategoryId(Long categoryId) {
        return categoryBrandRelationMapper.selectList(new LambdaQueryWrapper<CategoryBrandRelation>().eq(CategoryBrandRelation::getCatelogId,categoryId));
    }
}
