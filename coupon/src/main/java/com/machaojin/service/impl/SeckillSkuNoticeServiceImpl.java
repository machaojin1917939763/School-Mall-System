package com.machaojin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.SeckillSkuNoticeMapper;
import com.machaojin.domain.SeckillSkuNotice;
import com.machaojin.service.ISeckillSkuNoticeService;

/**
 * 秒杀商品通知订阅Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service
public class SeckillSkuNoticeServiceImpl implements ISeckillSkuNoticeService 
{
    @Autowired
    private SeckillSkuNoticeMapper seckillSkuNoticeMapper;

    /**
     * 查询秒杀商品通知订阅
     * 
     * @param id 秒杀商品通知订阅主键
     * @return 秒杀商品通知订阅
     */
    @Override
    public SeckillSkuNotice selectSeckillSkuNoticeById(Long id)
    {
        return seckillSkuNoticeMapper.selectSeckillSkuNoticeById(id);
    }

    /**
     * 查询秒杀商品通知订阅列表
     * 
     * @param seckillSkuNotice 秒杀商品通知订阅
     * @return 秒杀商品通知订阅
     */
    @Override
    public List<SeckillSkuNotice> selectSeckillSkuNoticeList(SeckillSkuNotice seckillSkuNotice)
    {
        return seckillSkuNoticeMapper.selectSeckillSkuNoticeList(seckillSkuNotice);
    }

    /**
     * 新增秒杀商品通知订阅
     * 
     * @param seckillSkuNotice 秒杀商品通知订阅
     * @return 结果
     */
    @Override
    public int insertSeckillSkuNotice(SeckillSkuNotice seckillSkuNotice)
    {
        return seckillSkuNoticeMapper.insertSeckillSkuNotice(seckillSkuNotice);
    }

    /**
     * 修改秒杀商品通知订阅
     * 
     * @param seckillSkuNotice 秒杀商品通知订阅
     * @return 结果
     */
    @Override
    public int updateSeckillSkuNotice(SeckillSkuNotice seckillSkuNotice)
    {
        return seckillSkuNoticeMapper.updateSeckillSkuNotice(seckillSkuNotice);
    }

    /**
     * 批量删除秒杀商品通知订阅
     * 
     * @param ids 需要删除的秒杀商品通知订阅主键
     * @return 结果
     */
    @Override
    public int deleteSeckillSkuNoticeByIds(Long[] ids)
    {
        return seckillSkuNoticeMapper.deleteSeckillSkuNoticeByIds(ids);
    }

    /**
     * 删除秒杀商品通知订阅信息
     * 
     * @param id 秒杀商品通知订阅主键
     * @return 结果
     */
    @Override
    public int deleteSeckillSkuNoticeById(Long id)
    {
        return seckillSkuNoticeMapper.deleteSeckillSkuNoticeById(id);
    }
}
