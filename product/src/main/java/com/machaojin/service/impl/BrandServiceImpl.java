package com.machaojin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.BrandMapper;
import com.machaojin.domain.Brand;
import com.machaojin.service.IBrandService;

/**
 * 品牌Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service
public class BrandServiceImpl implements IBrandService 
{
    @Autowired
    private BrandMapper brandMapper;

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
        return brandMapper.updateBrand(brand);
    }

    /**
     * 批量删除品牌
     * 
     * @param brandIds 需要删除的品牌主键
     * @return 结果
     */
    @Override
    public int deleteBrandByBrandIds(Long[] brandIds)
    {
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
        return brandMapper.deleteBrandByBrandId(brandId);
    }
}
