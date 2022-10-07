package com.machaojin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.WareSkuMapper;
import com.machaojin.domain.WareSku;
import com.machaojin.service.IWareSkuService;

/**
 * 商品库存Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-06
 */
@Service
public class WareSkuServiceImpl implements IWareSkuService 
{
    @Autowired
    private WareSkuMapper wareSkuMapper;

    /**
     * 查询商品库存
     * 
     * @param id 商品库存主键
     * @return 商品库存
     */
    @Override
    public WareSku selectWareSkuById(Long id)
    {
        return wareSkuMapper.selectWareSkuById(id);
    }

    /**
     * 查询商品库存列表
     * 
     * @param wareSku 商品库存
     * @return 商品库存
     */
    @Override
    public List<WareSku> selectWareSkuList(WareSku wareSku)
    {
        return wareSkuMapper.selectWareSkuList(wareSku);
    }

    /**
     * 新增商品库存
     * 
     * @param wareSku 商品库存
     * @return 结果
     */
    @Override
    public int insertWareSku(WareSku wareSku)
    {
        return wareSkuMapper.insertWareSku(wareSku);
    }

    /**
     * 修改商品库存
     * 
     * @param wareSku 商品库存
     * @return 结果
     */
    @Override
    public int updateWareSku(WareSku wareSku)
    {
        return wareSkuMapper.updateWareSku(wareSku);
    }

    /**
     * 批量删除商品库存
     * 
     * @param ids 需要删除的商品库存主键
     * @return 结果
     */
    @Override
    public int deleteWareSkuByIds(Long[] ids)
    {
        return wareSkuMapper.deleteWareSkuByIds(ids);
    }

    /**
     * 删除商品库存信息
     * 
     * @param id 商品库存主键
     * @return 结果
     */
    @Override
    public int deleteWareSkuById(Long id)
    {
        return wareSkuMapper.deleteWareSkuById(id);
    }
}
