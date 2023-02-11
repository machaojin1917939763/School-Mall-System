package com.machaojin.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.machaojin.domain.SkuInfo;
import com.machaojin.domain.SkuSaleAttrValue;
import com.machaojin.vo.SkuInfoVo;

/**
 * sku信息Service接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */

public interface ISkuInfoService extends IService<SkuInfo>
{
    /**
     * 查询sku信息
     * 
     * @param skuId sku信息主键
     * @return sku信息
     */
    public SkuInfo selectSkuInfoBySkuId(Long skuId);

    /**
     * 查询sku信息列表
     * 
     * @param skuInfo sku信息
     * @return sku信息集合
     */
    public List<SkuInfo> selectSkuInfoList(SkuInfoVo skuInfo);

    /**
     * 新增sku信息
     * 
     * @param skuInfo sku信息
     * @return 结果
     */
    public int insertSkuInfo(SkuInfo skuInfo);

    /**
     * 修改sku信息
     * 
     * @param skuInfo sku信息
     * @return 结果
     */
    public int updateSkuInfo(SkuInfo skuInfo);

    /**
     * 批量删除sku信息
     * 
     * @param skuIds 需要删除的sku信息主键集合
     * @return 结果
     */
    public int deleteSkuInfoBySkuIds(Long[] skuIds);

    /**
     * 删除sku信息信息
     * 
     * @param skuId sku信息主键
     * @return 结果
     */
    public int deleteSkuInfoBySkuId(Long skuId);
}
