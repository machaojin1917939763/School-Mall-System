package com.machaojin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.machaojin.domain.CategoryBrandRelation;
import com.machaojin.domain.SpuInfoDesc;
import com.machaojin.exception.DataDeleteException;
import com.machaojin.mapper.CategoryBrandRelationMapper;
import com.machaojin.mapper.SpuInfoDescMapper;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.CategoryMapper;
import com.machaojin.domain.Category;
import com.machaojin.service.ICategoryService;


/**
 * 商品三级分类Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService
{
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryBrandRelationMapper categoryBrandRelationMapper;

    /**
     * 查询商品三级分类
     * 
     * @param catId 商品三级分类主键
     * @return 商品三级分类
     */
    @Override
    public Category selectCategoryByCatId(Long catId)
    {
        return categoryMapper.selectCategoryByCatId(catId);
    }

    /**
     * 查询商品三级分类列表
     * 
     * @param category 商品三级分类
     * @return 商品三级分类
     */
    @Override
    public List<Category> selectCategoryList(Category category)
    {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 新增商品三级分类
     * 
     * @param category 商品三级分类
     * @return 结果
     *
     */
    @Override
    public int insertCategory(Category category)
    {
        return categoryMapper.insertCategory(category);
    }

    /**
     * 修改商品三级分类
     * 
     * @param category 商品三级分类
     * @return 结果
     */
    @Override
    public int updateCategory(Category category)
    {
           //修改关系表中的数据，根据category的id查询出来
        List<CategoryBrandRelation> categoryBrandRelations = categoryBrandRelationMapper.selectList(new LambdaQueryWrapper<CategoryBrandRelation>().eq(CategoryBrandRelation::getCatelogId, category.getCatId()));
        if (categoryBrandRelations != null && categoryBrandRelations.size() > 0){
            categoryBrandRelations.forEach((relation) -> {
                relation.setCatelogName(category.getName());
                categoryBrandRelationMapper.updateById(relation);
            });

        }
        log.error(category.toString());
        return categoryMapper.updateCategory(category);
    }

    /**
     * 批量删除商品三级分类
     * 
     * @param catIds 需要删除的商品三级分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByCatIds(Long[] catIds)
    {

        List<Long> longList = new ArrayList<>();
        Arrays.stream(catIds).forEach((a)->{
            if (categoryBrandRelationMapper.selectList(new LambdaQueryWrapper<CategoryBrandRelation>().eq(CategoryBrandRelation::getCatelogId,a)) != null){
                longList.add(a);
            }
        });
        if (longList.size() == 0){
            throw new DataDeleteException("没有数据可以删除");
        }

        //TODO 逻辑删除分类选项，判断删除的分类是否被其他模块引用，如果发生引用就不能删除
        return categoryMapper.deleteCategoryByCatIds(longList.toArray(new Long[0]));
    }

    /**
     * 删除商品三级分类信息
     * 
     * @param catId 商品三级分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByCatId(Long catId)
    {
        if (categoryBrandRelationMapper.selectList(new LambdaQueryWrapper<CategoryBrandRelation>().eq(CategoryBrandRelation::getCatelogId,catId)) != null){
            throw new DataDeleteException("数据删除失败，关联表中还有数据");
        }
        return categoryMapper.deleteCategoryByCatId(catId);
    }
}
