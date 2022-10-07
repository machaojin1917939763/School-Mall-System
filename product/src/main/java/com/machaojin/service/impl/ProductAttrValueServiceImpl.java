package com.machaojin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.ProductAttrValueMapper;
import com.machaojin.domain.ProductAttrValue;
import com.machaojin.service.IProductAttrValueService;

/**
 * spu属性值Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service
public class ProductAttrValueServiceImpl implements IProductAttrValueService 
{
    @Autowired
    private ProductAttrValueMapper productAttrValueMapper;

    /**
     * 查询spu属性值
     * 
     * @param id spu属性值主键
     * @return spu属性值
     */
    @Override
    public ProductAttrValue selectProductAttrValueById(Long id)
    {
        return productAttrValueMapper.selectProductAttrValueById(id);
    }

    /**
     * 查询spu属性值列表
     * 
     * @param productAttrValue spu属性值
     * @return spu属性值
     */
    @Override
    public List<ProductAttrValue> selectProductAttrValueList(ProductAttrValue productAttrValue)
    {
        return productAttrValueMapper.selectProductAttrValueList(productAttrValue);
    }

    /**
     * 新增spu属性值
     * 
     * @param productAttrValue spu属性值
     * @return 结果
     */
    @Override
    public int insertProductAttrValue(ProductAttrValue productAttrValue)
    {
        return productAttrValueMapper.insertProductAttrValue(productAttrValue);
    }

    /**
     * 修改spu属性值
     * 
     * @param productAttrValue spu属性值
     * @return 结果
     */
    @Override
    public int updateProductAttrValue(ProductAttrValue productAttrValue)
    {
        return productAttrValueMapper.updateProductAttrValue(productAttrValue);
    }

    /**
     * 批量删除spu属性值
     * 
     * @param ids 需要删除的spu属性值主键
     * @return 结果
     */
    @Override
    public int deleteProductAttrValueByIds(Long[] ids)
    {
        return productAttrValueMapper.deleteProductAttrValueByIds(ids);
    }

    /**
     * 删除spu属性值信息
     * 
     * @param id spu属性值主键
     * @return 结果
     */
    @Override
    public int deleteProductAttrValueById(Long id)
    {
        return productAttrValueMapper.deleteProductAttrValueById(id);
    }
}
