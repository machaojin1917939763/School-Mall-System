package com.machaojin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.PurchaseDetailMapper;
import com.machaojin.domain.PurchaseDetail;
import com.machaojin.service.IPurchaseDetailService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-06
 */
@Service
public class PurchaseDetailServiceImpl implements IPurchaseDetailService 
{
    @Autowired
    private PurchaseDetailMapper purchaseDetailMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public PurchaseDetail selectPurchaseDetailById(Long id)
    {
        return purchaseDetailMapper.selectPurchaseDetailById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param purchaseDetail 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<PurchaseDetail> selectPurchaseDetailList(PurchaseDetail purchaseDetail)
    {
        return purchaseDetailMapper.selectPurchaseDetailList(purchaseDetail);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param purchaseDetail 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertPurchaseDetail(PurchaseDetail purchaseDetail)
    {
        return purchaseDetailMapper.insertPurchaseDetail(purchaseDetail);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param purchaseDetail 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updatePurchaseDetail(PurchaseDetail purchaseDetail)
    {
        return purchaseDetailMapper.updatePurchaseDetail(purchaseDetail);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deletePurchaseDetailByIds(Long[] ids)
    {
        return purchaseDetailMapper.deletePurchaseDetailByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deletePurchaseDetailById(Long id)
    {
        return purchaseDetailMapper.deletePurchaseDetailById(id);
    }
}
