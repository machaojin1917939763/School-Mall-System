package com.machaojin.service.impl;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.ParserConfig;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.machaojin.domain.CategoryBrandRelation;
import com.machaojin.domain.SpuInfoDesc;
import com.machaojin.exception.DataDeleteException;
import com.machaojin.mapper.CategoryBrandRelationMapper;
import com.machaojin.mapper.SpuInfoDescMapper;
import com.machaojin.vo.Category2Vo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.CategoryMapper;
import com.machaojin.domain.Category;
import com.machaojin.service.ICategoryService;
import org.springframework.util.StringUtils;


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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 查询商品三级分类
     * 
     * @param catId 商品三级分类主键
     * @return 商品三级分类
     */
    @Cacheable(value = "product_category",key = "#root.methodName")
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
    @Cacheable(value = "product_category",key = "#root.methodName")
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
    @CacheEvict(value = "product_category",allEntries = true)
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
    @CacheEvict(value = "product_category",allEntries = true)
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
     * 批量删除商品三级分类,需要删除多个的时候，使用Caching
     * 
     * @param catIds 需要删除的商品三级分类主键
     * @return 结果
     */
//    @CacheEvict(value = {"product_category_level2"},key = "'getCategoryLevel1'")
    @Caching(evict = {
            @CacheEvict(value = {"product_category"},key = "'getCategoryLevel1'"),
            @CacheEvict(value = {"product_category"},key = "'getCategoryLevel23'")})
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
     * allEntries = true:删除分区内的所有数据
     * @param catId 商品三级分类主键
     * @return 结果
     */
    @CacheEvict(value = "product_category",allEntries = true)
    @Override
    public int deleteCategoryByCatId(Long catId)
    {
        if (categoryBrandRelationMapper.selectList(new LambdaQueryWrapper<CategoryBrandRelation>().eq(CategoryBrandRelation::getCatelogId,catId)) != null){
            throw new DataDeleteException("数据删除失败，关联表中还有数据");
        }
        return categoryMapper.deleteCategoryByCatId(catId);
    }

    /**
     * 查询商品的一级分类
     * @return 返回商品的以及分类
     */
    @Cacheable(value = {"product_category"},key = "#root.methodName")
    @Override
    public List<Category> getCategoryLevel1() {
        return getCategoryLevel1Redis();
    }

    public List<Category> getCategoryLevel1Redis() {
//        //获取缓存
//        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
//        String s = ops.get("com/machaojin/product/category_level1");
//        if (!StringUtils.isEmpty(s)){
//            return JSON.parseArray(s, Category.class);
//        }else{
//            List<Category> list = this.list(new LambdaQueryWrapper<Category>().eq(Category::getCatLevel, "1"));
//            ops.set("com/machaojin/product/category_level1",JSON.toJSONString(list),30, TimeUnit.MINUTES);
//            return list;
//        }
        return this.list(new LambdaQueryWrapper<Category>().eq(Category::getCatLevel, "1"));
    }

    /**
     * 根据一级分类获取二级三级分类
     * sync = true:当存在缓存击穿的问题时，锁住线程，只放一个线程去请求数据库，是本地锁，一个服务放一个线程过去
     * @return 返回23级分类
     */
    @Cacheable(value = {"product_category"},key = "#root.methodName",sync = true)
    @Override
    public HashMap<String,List<Category2Vo>> getCategoryLevel23() {
//        //获取缓存
//        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
//        String level23 = ops.get("com/machaojin/product/category_level23");
//        if (!StringUtils.isEmpty(level23)){
//           return JSON.parseObject(level23,new TypeReference<HashMap<String,List<Category2Vo>>>(){});
//        }else{
//            HashMap<String, List<Category2Vo>> categoryLevel23Redis = getCategoryLevel23Redis();
//            ops.set("com/machaojin/product/category_level23",JSON.toJSONString(categoryLevel23Redis),30,TimeUnit.MINUTES);
//            return categoryLevel23Redis;
//        }
        return getCategoryLevel23Redis();
    }

    public HashMap<String,List<Category2Vo>> getCategoryLevel23Redis(){
        //找到所有的一级分类
        List<Category> categoryLevel1 = this.getCategoryLevel1();
        //查询所有的分类数据
        List<Category> list = this.list();
        //找到所有的二级分类
        List<Category> cateLevel2 = list.stream().filter((category -> {
            return category.getCatLevel() == 2;
        })).collect(Collectors.toList());
        //找到所有的三级分类
        List<Category> cateLevel3 = list.stream().filter((category -> {
            return category.getCatLevel() == 3;
        })).collect(Collectors.toList());
        //遍历所有的二级分类
        List<Category2Vo> category2Vos = cateLevel2.stream().map((category -> {
            Category2Vo category2Vo = new Category2Vo();
            category2Vo.setId(category.getCatId().toString());
            category2Vo.setName(category.getName());
            category2Vo.setCatalog1Id(category.getParentCid().toString());
            //在三级分类中找到二级分类相等的
            category2Vo.setCatalog3List(cateLevel3.stream().filter((category1 -> {
                return Objects.equals(category1.getParentCid(), category.getCatId());
            })).map((category1 -> {
                Category2Vo.Category3Vo category3Vo = new Category2Vo.Category3Vo();
                category3Vo.setCatalog2Id(category1.getParentCid().toString());
                category3Vo.setId(category1.getCatId().toString());
                category3Vo.setName(category1.getName());
                return category3Vo;
            })).collect(Collectors.toList()));
            return category2Vo;
        })).collect(Collectors.toList());
        HashMap<String,List<Category2Vo>> listHashMap = new HashMap<>();
        categoryLevel1.forEach((category -> {
            List<Category2Vo> equalsCate = new ArrayList<>();
            category2Vos.forEach((category2Vo -> {
                if (category2Vo.getCatalog1Id().equals(category.getCatId().toString())){
                    equalsCate.add(category2Vo);
                }
            }));
            listHashMap.put(category.getCatId().toString(),equalsCate);
        }));
        return listHashMap;
    }
}
