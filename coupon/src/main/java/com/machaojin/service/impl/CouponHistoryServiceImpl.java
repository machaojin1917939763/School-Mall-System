package com.machaojin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.CouponHistoryMapper;
import com.machaojin.domain.CouponHistory;
import com.machaojin.service.ICouponHistoryService;

/**
 * 优惠券领取历史记录Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service
public class CouponHistoryServiceImpl implements ICouponHistoryService 
{
    @Autowired
    private CouponHistoryMapper couponHistoryMapper;

    /**
     * 查询优惠券领取历史记录
     * 
     * @param id 优惠券领取历史记录主键
     * @return 优惠券领取历史记录
     */
    @Override
    public CouponHistory selectCouponHistoryById(Long id)
    {
        return couponHistoryMapper.selectCouponHistoryById(id);
    }

    /**
     * 查询优惠券领取历史记录列表
     * 
     * @param couponHistory 优惠券领取历史记录
     * @return 优惠券领取历史记录
     */
    @Override
    public List<CouponHistory> selectCouponHistoryList(CouponHistory couponHistory)
    {
        return couponHistoryMapper.selectCouponHistoryList(couponHistory);
    }

    /**
     * 新增优惠券领取历史记录
     * 
     * @param couponHistory 优惠券领取历史记录
     * @return 结果
     */
    @Override
    public int insertCouponHistory(CouponHistory couponHistory)
    {
        couponHistory.setCreateTime(DateUtils.getNowDate());
        return couponHistoryMapper.insertCouponHistory(couponHistory);
    }

    /**
     * 修改优惠券领取历史记录
     * 
     * @param couponHistory 优惠券领取历史记录
     * @return 结果
     */
    @Override
    public int updateCouponHistory(CouponHistory couponHistory)
    {
        return couponHistoryMapper.updateCouponHistory(couponHistory);
    }

    /**
     * 批量删除优惠券领取历史记录
     * 
     * @param ids 需要删除的优惠券领取历史记录主键
     * @return 结果
     */
    @Override
    public int deleteCouponHistoryByIds(Long[] ids)
    {
        return couponHistoryMapper.deleteCouponHistoryByIds(ids);
    }

    /**
     * 删除优惠券领取历史记录信息
     * 
     * @param id 优惠券领取历史记录主键
     * @return 结果
     */
    @Override
    public int deleteCouponHistoryById(Long id)
    {
        return couponHistoryMapper.deleteCouponHistoryById(id);
    }
}
