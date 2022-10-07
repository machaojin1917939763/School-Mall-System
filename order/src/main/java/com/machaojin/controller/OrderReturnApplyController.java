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
import com.machaojin.domain.OrderReturnApply;
import com.machaojin.service.IOrderReturnApplyService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 订单退货申请Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/apply")
public class OrderReturnApplyController extends BaseController
{
    @Autowired
    private IOrderReturnApplyService orderReturnApplyService;

    /**
     * 查询订单退货申请列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:apply:list')")
    @GetMapping("/list")
    public TableDataInfo list(OrderReturnApply orderReturnApply)
    {
        startPage();
        List<OrderReturnApply> list = orderReturnApplyService.selectOrderReturnApplyList(orderReturnApply);
        return getDataTable(list);
    }

    /**
     * 导出订单退货申请列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:apply:export')")
    @Log(title = "订单退货申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderReturnApply orderReturnApply)
    {
        List<OrderReturnApply> list = orderReturnApplyService.selectOrderReturnApplyList(orderReturnApply);
        ExcelUtil<OrderReturnApply> util = new ExcelUtil<OrderReturnApply>(OrderReturnApply.class);
        util.exportExcel(response, list, "订单退货申请数据");
    }

    /**
     * 获取订单退货申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:apply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(orderReturnApplyService.selectOrderReturnApplyById(id));
    }

    /**
     * 新增订单退货申请
     */
    @PreAuthorize("@ss.hasPermi('machaojin:apply:add')")
    @Log(title = "订单退货申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderReturnApply orderReturnApply)
    {
        return toAjax(orderReturnApplyService.insertOrderReturnApply(orderReturnApply));
    }

    /**
     * 修改订单退货申请
     */
    @PreAuthorize("@ss.hasPermi('machaojin:apply:edit')")
    @Log(title = "订单退货申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderReturnApply orderReturnApply)
    {
        return toAjax(orderReturnApplyService.updateOrderReturnApply(orderReturnApply));
    }

    /**
     * 删除订单退货申请
     */
    @PreAuthorize("@ss.hasPermi('machaojin:apply:remove')")
    @Log(title = "订单退货申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderReturnApplyService.deleteOrderReturnApplyByIds(ids));
    }
}
