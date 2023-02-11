package com.machaojin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.machaojin.domain.Brand;
import com.machaojin.domain.Category;

/**
 * 品牌Mapper接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@org.apache.ibatis.annotations.Mapper
public interface BrandMapper extends BaseMapper<Brand>
{
    /**
     * 查询品牌
     * 
     * @param brandId 品牌主键
     * @return 品牌
     */
    public Brand selectBrandByBrandId(Long brandId);

    /**
     * 查询品牌列表
     * 
     * @param brand 品牌
     * @return 品牌集合
     */
    public List<Brand> selectBrandList(Brand brand);

    /**
     * 新增品牌
     * 
     * @param brand 品牌
     * @return 结果
     */
    public int insertBrand(Brand brand);

    /**
     * 修改品牌
     * 
     * @param brand 品牌
     * @return 结果
     */
    public int updateBrand(Brand brand);

    /**
     * 删除品牌
     * 
     * @param brandId 品牌主键
     * @return 结果
     */
    public int deleteBrandByBrandId(Long brandId);

    /**
     * 批量删除品牌
     * 
     * @param brandIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrandByBrandIds(Long[] brandIds);
}
