package com.machaojin.mapper;

import java.util.List;
import com.machaojin.domain.OrderItem;

/**
 * 订单项信息Mapper接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@org.apache.ibatis.annotations.Mapper
public interface OrderItemMapper 
{
    /**
     * 查询订单项信息
     * 
     * @param id 订单项信息主键
     * @return 订单项信息
     */
    public OrderItem selectOrderItemById(Long id);

    /**
     * 查询订单项信息列表
     * 
     * @param orderItem 订单项信息
     * @return 订单项信息集合
     */
    public List<OrderItem> selectOrderItemList(OrderItem orderItem);

    /**
     * 新增订单项信息
     * 
     * @param orderItem 订单项信息
     * @return 结果
     */
    public int insertOrderItem(OrderItem orderItem);

    /**
     * 修改订单项信息
     * 
     * @param orderItem 订单项信息
     * @return 结果
     */
    public int updateOrderItem(OrderItem orderItem);

    /**
     * 删除订单项信息
     * 
     * @param id 订单项信息主键
     * @return 结果
     */
    public int deleteOrderItemById(Long id);

    /**
     * 批量删除订单项信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderItemByIds(Long[] ids);
}
