package com.machaojin.mapper;

import java.util.List;
import com.machaojin.domain.Category;

/**
 * 商品三级分类Mapper接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@org.apache.ibatis.annotations.Mapper
public interface CategoryMapper 
{
    /**
     * 查询商品三级分类
     * 
     * @param catId 商品三级分类主键
     * @return 商品三级分类
     */
    public Category selectCategoryByCatId(Long catId);

    /**
     * 查询商品三级分类列表
     * 
     * @param category 商品三级分类
     * @return 商品三级分类集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 新增商品三级分类
     * 
     * @param category 商品三级分类
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改商品三级分类
     * 
     * @param category 商品三级分类
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 删除商品三级分类
     * 
     * @param catId 商品三级分类主键
     * @return 结果
     */
    public int deleteCategoryByCatId(Long catId);

    /**
     * 批量删除商品三级分类
     * 
     * @param catIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryByCatIds(Long[] catIds);


}
