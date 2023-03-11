package com.machaojin.service;

import java.util.HashMap;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.machaojin.domain.Category;
import com.machaojin.domain.CommentReplay;
import com.machaojin.vo.Category2Vo;

/**
 * 商品三级分类Service接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */

public interface ICategoryService extends IService<Category>
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
     * 批量删除商品三级分类
     * 
     * @param catIds 需要删除的商品三级分类主键集合
     * @return 结果
     */
    public int deleteCategoryByCatIds(Long[] catIds);

    /**
     * 删除商品三级分类信息
     * 
     * @param catId 商品三级分类主键
     * @return 结果
     */
    public int deleteCategoryByCatId(Long catId);

    /**
     * 查询商品的一级分类
     * @return 返回商品的以及分类
     */
    List<Category> getCategoryLevel1();

    /**
     * 根据一级分类获取二级三级分类
     * @return 返回23级分类
     */
    HashMap<String,List<Category2Vo>> getCategoryLevel23();
}
