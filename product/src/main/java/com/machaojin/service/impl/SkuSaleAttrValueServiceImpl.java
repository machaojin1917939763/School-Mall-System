package com.machaojin.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.SkuSaleAttrValueMapper;
import com.machaojin.domain.SkuSaleAttrValue;
import com.machaojin.service.ISkuSaleAttrValueService;

/**
 * sku销售属性&值Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueMapper, SkuSaleAttrValue> implements ISkuSaleAttrValueService
{
    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    /**
     * 查询sku销售属性&值
     * 
     * @param id sku销售属性&值主键
     * @return sku销售属性&值
     */
    @Override
    public SkuSaleAttrValue selectSkuSaleAttrValueById(Long id)
    {
        return skuSaleAttrValueMapper.selectSkuSaleAttrValueById(id);
    }

    /**
     * 查询sku销售属性&值列表
     * 
     * @param skuSaleAttrValue sku销售属性&值
     * @return sku销售属性&值
     */
    @Override
    public List<SkuSaleAttrValue> selectSkuSaleAttrValueList(SkuSaleAttrValue skuSaleAttrValue)
    {
        return skuSaleAttrValueMapper.selectSkuSaleAttrValueList(skuSaleAttrValue);
    }

    /**
     * 新增sku销售属性&值
     * 
     * @param skuSaleAttrValue sku销售属性&值
     * @return 结果
     */
    @Override
    public int insertSkuSaleAttrValue(SkuSaleAttrValue skuSaleAttrValue)
    {
        return skuSaleAttrValueMapper.insertSkuSaleAttrValue(skuSaleAttrValue);
    }

    /**
     * 修改sku销售属性&值
     * 
     * @param skuSaleAttrValue sku销售属性&值
     * @return 结果
     */
    @Override
    public int updateSkuSaleAttrValue(SkuSaleAttrValue skuSaleAttrValue)
    {
        return skuSaleAttrValueMapper.updateSkuSaleAttrValue(skuSaleAttrValue);
    }

    /**
     * 批量删除sku销售属性&值
     * 
     * @param ids 需要删除的sku销售属性&值主键
     * @return 结果
     */
    @Override
    public int deleteSkuSaleAttrValueByIds(Long[] ids)
    {
        return skuSaleAttrValueMapper.deleteSkuSaleAttrValueByIds(ids);
    }

    /**
     * 删除sku销售属性&值信息
     * 
     * @param id sku销售属性&值主键
     * @return 结果
     */
    @Override
    public int deleteSkuSaleAttrValueById(Long id)
    {
        return skuSaleAttrValueMapper.deleteSkuSaleAttrValueById(id);
    }

}
