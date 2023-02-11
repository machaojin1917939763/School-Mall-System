package com.machaojin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.machaojin.MergeVo;
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
import com.machaojin.domain.Purchase;
import com.machaojin.service.IPurchaseService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 采购信息Controller
 * 
 * @author machaojin
 * @date 2022-10-06
 */
@RestController
@RequestMapping("/machaojin/purchase")
public class PurchaseController extends BaseController
{
    @Autowired
    private IPurchaseService purchaseService;

    /**
     * 查询采购信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:purchase:list')")
    @GetMapping("/list")
    public TableDataInfo list(Purchase purchase)
    {
        startPage();
        List<Purchase> list = purchaseService.selectPurchaseList(purchase);
        return getDataTable(list);
    }

    /**
     * 合并采购需求到采购单上
     */
    @PreAuthorize("@ss.hasPermi('machaojin:purchase:update')")
    @PostMapping("/merge")
    public AjaxResult merge(@RequestBody  MergeVo mergeVo){
        return AjaxResult.success(purchaseService.mergePurchase(mergeVo));
    }

    /**
     * 查询未合并的采购信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:purchase:list')")
    @GetMapping("/list/new")
    public TableDataInfo listForStatus(Purchase purchase)
    {
        startPage();
        List<Purchase> list = purchaseService.selectUnPurchaseList(purchase);
        return getDataTable(list);
    }

    /**
     * 导出采购信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:purchase:export')")
    @Log(title = "采购信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Purchase purchase)
    {
        List<Purchase> list = purchaseService.selectPurchaseList(purchase);
        ExcelUtil<Purchase> util = new ExcelUtil<Purchase>(Purchase.class);
        util.exportExcel(response, list, "采购信息数据");
    }

    /**
     * 获取采购信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:purchase:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(purchaseService.selectPurchaseById(id));
    }

    /**
     * 新增采购信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:purchase:add')")
    @Log(title = "采购信息", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult add(@RequestBody Purchase purchase)
    {
        return toAjax(purchaseService.insertPurchase(purchase));
    }

    /**
     * 修改采购信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:purchase:edit')")
    @Log(title = "采购信息", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody Purchase purchase)
    {
        return toAjax(purchaseService.updatePurchase(purchase));
    }

    /**
     * 删除采购信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:purchase:remove')")
    @Log(title = "采购信息", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(purchaseService.deletePurchaseByIds(ids));
    }
}
