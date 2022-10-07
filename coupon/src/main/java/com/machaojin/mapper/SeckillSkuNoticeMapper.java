package com.machaojin.mapper;

import java.util.List;
import com.machaojin.domain.SeckillSkuNotice;

/**
 * 秒杀商品通知订阅Mapper接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@org.apache.ibatis.annotations.Mapper
public interface SeckillSkuNoticeMapper 
{
    /**
     * 查询秒杀商品通知订阅
     * 
     * @param id 秒杀商品通知订阅主键
     * @return 秒杀商品通知订阅
     */
    public SeckillSkuNotice selectSeckillSkuNoticeById(Long id);

    /**
     * 查询秒杀商品通知订阅列表
     * 
     * @param seckillSkuNotice 秒杀商品通知订阅
     * @return 秒杀商品通知订阅集合
     */
    public List<SeckillSkuNotice> selectSeckillSkuNoticeList(SeckillSkuNotice seckillSkuNotice);

    /**
     * 新增秒杀商品通知订阅
     * 
     * @param seckillSkuNotice 秒杀商品通知订阅
     * @return 结果
     */
    public int insertSeckillSkuNotice(SeckillSkuNotice seckillSkuNotice);

    /**
     * 修改秒杀商品通知订阅
     * 
     * @param seckillSkuNotice 秒杀商品通知订阅
     * @return 结果
     */
    public int updateSeckillSkuNotice(SeckillSkuNotice seckillSkuNotice);

    /**
     * 删除秒杀商品通知订阅
     * 
     * @param id 秒杀商品通知订阅主键
     * @return 结果
     */
    public int deleteSeckillSkuNoticeById(Long id);

    /**
     * 批量删除秒杀商品通知订阅
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSeckillSkuNoticeByIds(Long[] ids);
}
