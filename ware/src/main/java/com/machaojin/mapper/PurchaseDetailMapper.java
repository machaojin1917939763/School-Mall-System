package com.machaojin.mapper;

import java.util.List;
import com.machaojin.domain.PurchaseDetail;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author machaojin
 * @date 2022-10-06
 */
@org.apache.ibatis.annotations.Mapper
public interface PurchaseDetailMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public PurchaseDetail selectPurchaseDetailById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param purchaseDetail 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<PurchaseDetail> selectPurchaseDetailList(PurchaseDetail purchaseDetail);

    /**
     * 新增【请填写功能名称】
     * 
     * @param purchaseDetail 【请填写功能名称】
     * @return 结果
     */
    public int insertPurchaseDetail(PurchaseDetail purchaseDetail);

    /**
     * 修改【请填写功能名称】
     * 
     * @param purchaseDetail 【请填写功能名称】
     * @return 结果
     */
    public int updatePurchaseDetail(PurchaseDetail purchaseDetail);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deletePurchaseDetailById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePurchaseDetailByIds(Long[] ids);
}
