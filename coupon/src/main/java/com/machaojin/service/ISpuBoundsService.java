package com.machaojin.service;

import java.util.List;
import com.machaojin.domain.SpuBounds;

/**
 * 商品spu积分设置Service接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */

public interface ISpuBoundsService 
{
    /**
     * 查询商品spu积分设置
     * 
     * @param id 商品spu积分设置主键
     * @return 商品spu积分设置
     */
    public SpuBounds selectSpuBoundsById(Long id);

    /**
     * 查询商品spu积分设置列表
     * 
     * @param spuBounds 商品spu积分设置
     * @return 商品spu积分设置集合
     */
    public List<SpuBounds> selectSpuBoundsList(SpuBounds spuBounds);

    /**
     * 新增商品spu积分设置
     * 
     * @param spuBounds 商品spu积分设置
     * @return 结果
     */
    public int insertSpuBounds(SpuBounds spuBounds);

    /**
     * 修改商品spu积分设置
     * 
     * @param spuBounds 商品spu积分设置
     * @return 结果
     */
    public int updateSpuBounds(SpuBounds spuBounds);

    /**
     * 批量删除商品spu积分设置
     * 
     * @param ids 需要删除的商品spu积分设置主键集合
     * @return 结果
     */
    public int deleteSpuBoundsByIds(Long[] ids);

    /**
     * 删除商品spu积分设置信息
     * 
     * @param id 商品spu积分设置主键
     * @return 结果
     */
    public int deleteSpuBoundsById(Long id);
}
