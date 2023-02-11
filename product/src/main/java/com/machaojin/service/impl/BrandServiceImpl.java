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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.BrandMapper;
import com.machaojin.domain.Brand;
import com.machaojin.service.IBrandService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 品牌Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service

public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService
{
    @Autowired
    private BrandMapper brandMapper;

    @Autowired
     private CategoryBrandRelationMapper categoryBrandRelationMapper;

    /**
     * 查询品牌
     * 
     * @param brandId 品牌主键
     * @return 品牌
     */
    @Override
    public Brand selectBrandByBrandId(Long brandId)
    {
        return brandMapper.selectBrandByBrandId(brandId);
    }

    /**
     * 查询品牌列表
     * 
     * @param brand 品牌
     * @return 品牌
     */
    @Override
    public List<Brand> selectBrandList(Brand brand)
    {
        return brandMapper.selectBrandList(brand);
    }

    /**
     * 新增品牌
     * 
     * @param brand 品牌
     * @return 结果
     */
    @Override
    public int insertBrand(Brand brand)
    {
        return brandMapper.insertBrand(brand);
    }

    /**
     * 修改品牌
     * 
     * @param brand 品牌
     * @return 结果
     */
    @Override
    public int updateBrand(Brand brand)
    {

        //更新关系表中的数据
        if (StringUtils.isNotEmpty(brand.getName())){

            CategoryBrandRelation categoryBrandRelation = new CategoryBrandRelation();
            categoryBrandRelation.setBrandName(brand.getName());
            LambdaQueryWrapper<CategoryBrandRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(CategoryBrandRelation::getBrandId,brand.getBrandId());

            categoryBrandRelationMapper.update(categoryBrandRelation,lambdaQueryWrapper);
        }
        return brandMapper.updateBrand(brand);
    }

    /**
     * 批量删除品牌
     * 
     * @param brandIds 需要删除的品牌主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBrandByBrandIds(Long[] brandIds)
    {
        List<Long> cateAndBrandIds = new ArrayList<>();
        Arrays.stream(brandIds).forEach((a)->{
            //查询关系表中的品牌和商品的数据
            List<CategoryBrandRelation> categoryBrandRelations = categoryBrandRelationMapper.selectList(new LambdaQueryWrapper<CategoryBrandRelation>().eq(CategoryBrandRelation::getBrandId, a));
            if (categoryBrandRelations != null && categoryBrandRelations.size() != 0){
                categoryBrandRelations.forEach((cateAndBrandId) -> {
                    cateAndBrandIds.add(cateAndBrandId.getId());
                });
            }
        });
        //如果关系表中没有数据，就可以直接删除
        if (cateAndBrandIds.size() != 0){
            categoryBrandRelationMapper.deleteCategoryBrandRelationByIds(cateAndBrandIds.toArray(new Long[]{}));
        }
        return brandMapper.deleteBrandByBrandIds(brandIds);
    }

    /**
     * 删除品牌信息
     * 
     * @param brandId 品牌主键
     * @return 结果
     */
    @Override
    public int deleteBrandByBrandId(Long brandId)
    {
        if (categoryBrandRelationMapper.selectList(new LambdaQueryWrapper<CategoryBrandRelation>().eq(CategoryBrandRelation::getBrandId,brandId)) != null){
            throw new DataDeleteException("数据关联，不能删除");
        }
        return brandMapper.deleteBrandByBrandId(brandId);
    }
}
