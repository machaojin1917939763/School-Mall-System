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
import com.machaojin.domain.OrderSetting;
import com.machaojin.service.IOrderSettingService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 订单配置信息Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/setting")
public class OrderSettingController extends BaseController
{
    @Autowired
    private IOrderSettingService orderSettingService;

    /**
     * 查询订单配置信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:setting:list')")
    @GetMapping("/list")
    public TableDataInfo list(OrderSetting orderSetting)
    {
        startPage();
        List<OrderSetting> list = orderSettingService.selectOrderSettingList(orderSetting);
        return getDataTable(list);
    }

    /**
     * 导出订单配置信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:setting:export')")
    @Log(title = "订单配置信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderSetting orderSetting)
    {
        List<OrderSetting> list = orderSettingService.selectOrderSettingList(orderSetting);
        ExcelUtil<OrderSetting> util = new ExcelUtil<OrderSetting>(OrderSetting.class);
        util.exportExcel(response, list, "订单配置信息数据");
    }

    /**
     * 获取订单配置信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:setting:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(orderSettingService.selectOrderSettingById(id));
    }

    /**
     * 新增订单配置信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:setting:add')")
    @Log(title = "订单配置信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderSetting orderSetting)
    {
        return toAjax(orderSettingService.insertOrderSetting(orderSetting));
    }

    /**
     * 修改订单配置信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:setting:edit')")
    @Log(title = "订单配置信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderSetting orderSetting)
    {
        return toAjax(orderSettingService.updateOrderSetting(orderSetting));
    }

    /**
     * 删除订单配置信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:setting:remove')")
    @Log(title = "订单配置信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderSettingService.deleteOrderSettingByIds(ids));
    }
}
