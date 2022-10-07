package com.machaojin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.machaojin.domain.OrderItem;
import com.machaojin.service.IOrderItemService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 订单项信息Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/item")
public class OrderItemController extends BaseController
{
    @Autowired
    private IOrderItemService orderItemService;

    /**
     * 查询订单项信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(OrderItem orderItem)
    {
        startPage();
        List<OrderItem> list = orderItemService.selectOrderItemList(orderItem);
        return getDataTable(list);
    }

    /**
     * 导出订单项信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:item:export')")
    @Log(title = "订单项信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderItem orderItem)
    {
        List<OrderItem> list = orderItemService.selectOrderItemList(orderItem);
        ExcelUtil<OrderItem> util = new ExcelUtil<OrderItem>(OrderItem.class);
        util.exportExcel(response, list, "订单项信息数据");
    }

    /**
     * 获取订单项信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(orderItemService.selectOrderItemById(id));
    }

    /**
     * 新增订单项信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:item:add')")
    @Log(title = "订单项信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderItem orderItem)
    {
        return toAjax(orderItemService.insertOrderItem(orderItem));
    }

    /**
     * 修改订单项信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:item:edit')")
    @Log(title = "订单项信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderItem orderItem)
    {
        return toAjax(orderItemService.updateOrderItem(orderItem));
    }

    /**
     * 删除订单项信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:item:remove')")
    @Log(title = "订单项信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderItemService.deleteOrderItemByIds(ids));
    }
}
