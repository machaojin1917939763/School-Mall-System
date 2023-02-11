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
import com.machaojin.domain.PurchaseDetail;
import com.machaojin.service.IPurchaseDetailService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author machaojin
 * @date 2022-10-06
 */
@RestController
@RequestMapping("/machaojin/purchasedetail")
public class PurchaseDetailController extends BaseController
{
    @Autowired
    private IPurchaseDetailService purchaseDetailService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(PurchaseDetail purchaseDetail)
    {
        startPage();
        List<PurchaseDetail> list = purchaseDetailService.selectPurchaseDetailList(purchaseDetail);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:detail:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PurchaseDetail purchaseDetail)
    {
        List<PurchaseDetail> list = purchaseDetailService.selectPurchaseDetailList(purchaseDetail);
        ExcelUtil<PurchaseDetail> util = new ExcelUtil<PurchaseDetail>(PurchaseDetail.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:detail:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(purchaseDetailService.selectPurchaseDetailById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('machaojin:detail:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult add(@RequestBody PurchaseDetail purchaseDetail)
    {
        return toAjax(purchaseDetailService.insertPurchaseDetail(purchaseDetail));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('machaojin:detail:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody PurchaseDetail purchaseDetail)
    {
        return toAjax(purchaseDetailService.updatePurchaseDetail(purchaseDetail));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('machaojin:detail:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(purchaseDetailService.deletePurchaseDetailByIds(ids));
    }
}
