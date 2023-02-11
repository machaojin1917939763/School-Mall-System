package com.machaojin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.machaojin.MergeVo;
import com.machaojin.basecode.Ware;
import com.machaojin.domain.PurchaseDetail;
import com.machaojin.service.IPurchaseDetailService;
import com.ruoyi.common.utils.DateUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.PurchaseMapper;
import com.machaojin.domain.Purchase;
import com.machaojin.service.IPurchaseService;

/**
 * 采购信息Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-06
 */
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper,Purchase> implements IPurchaseService
{
    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private IPurchaseDetailService purchaseDetailService;

    /**
     * 查询采购信息
     * 
     * @param id 采购信息主键
     * @return 采购信息
     */
    @Override
    public Purchase selectPurchaseById(Long id)
    {
        return purchaseMapper.selectPurchaseById(id);
    }

    /**
     * 查询采购信息列表
     * 
     * @param purchase 采购信息
     * @return 采购信息
     */
    @Override
    public List<Purchase> selectPurchaseList(Purchase purchase)
    {
        return purchaseMapper.selectPurchaseList(purchase);
    }

    /**
     * 新增采购信息
     * 
     * @param purchase 采购信息
     * @return 结果
     */
    @Override
    public int insertPurchase(Purchase purchase)
    {
        purchase.setCreateTime(DateUtils.getNowDate());
        return purchaseMapper.insertPurchase(purchase);
    }

    /**
     * 修改采购信息
     * 
     * @param purchase 采购信息
     * @return 结果
     */
    @Override
    public int updatePurchase(Purchase purchase)
    {
        purchase.setUpdateTime(DateUtils.getNowDate());
        return purchaseMapper.updatePurchase(purchase);
    }

    /**
     * 批量删除采购信息
     * 
     * @param ids 需要删除的采购信息主键
     * @return 结果
     */
    @Override
    public int deletePurchaseByIds(Long[] ids)
    {
        return purchaseMapper.deletePurchaseByIds(ids);
    }

    /**
     * 删除采购信息信息
     * 
     * @param id 采购信息主键
     * @return 结果
     */
    @Override
    public int deletePurchaseById(Long id)
    {
        return purchaseMapper.deletePurchaseById(id);
    }

    /**
     * 查询未合并的采购信息列表
     *
     * @param purchase 采购信息
     * @return 采购信息集合
     */
    @Override
    public List<Purchase> selectUnPurchaseList(Purchase purchase) {
        return purchaseMapper.selectUnPurchaseList(purchase);
    }

    /**
     * 合并采购需求到采购单上
     */
    @Override
    public int mergePurchase(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        List<Long> item = mergeVo.getItems();
        PurchaseDetail purchaseDetail1 = purchaseDetailService.selectPurchaseDetailById(item.get(0));
        if (purchaseId == null){
            Purchase purchase = new Purchase();
            purchase.setCreateTime(new Date());
            purchase.setUpdateTime(new Date());
            purchase.setStatus(Ware.PurchaseCode.ASSIGNED.getCode());
            purchase.setWareId(purchaseDetail1.getWareId());
            this.save(purchase);
            purchaseId = purchase.getId();
        }
        Long id = purchaseId;
        Purchase purchase = new Purchase();
        purchase.setUpdateTime(new Date());
        purchase.setId(id);
        purchase.setStatus(Ware.PurchaseCode.ASSIGNED.getCode());
        purchase.setWareId(purchaseDetail1.getWareId());
        this.updateById(purchase);
            item.forEach((de) -> {
                PurchaseDetail purchaseDetail = new PurchaseDetail();
                purchaseDetail.setPurchaseId(id);
                purchaseDetail.setId(de);
                purchaseDetail.setStatus(Ware.PurchaseDetailCode.ASSIGNED.getCode().longValue());
                purchaseDetailService.updatePurchaseDetail(purchaseDetail);
            });
            return 1;
    }
}
