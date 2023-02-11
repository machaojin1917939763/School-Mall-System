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
import com.machaojin.domain.WareSku;
import com.machaojin.service.IWareSkuService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 商品库存Controller
 * 
 * @author machaojin
 * @date 2022-10-06
 */
@RestController
@RequestMapping("/machaojin/ware/sku")
public class WareSkuController extends BaseController
{
    @Autowired
    private IWareSkuService wareSkuService;

    /**
     * 查询商品库存列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:sku:list')")
    @GetMapping("/list")
    public TableDataInfo list(WareSku wareSku)
    {
        startPage();
        List<WareSku> list = wareSkuService.selectWareSkuList(wareSku);
        return getDataTable(list);
    }

    /**
     * 导出商品库存列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:sku:export')")
    @Log(title = "商品库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WareSku wareSku)
    {
        List<WareSku> list = wareSkuService.selectWareSkuList(wareSku);
        ExcelUtil<WareSku> util = new ExcelUtil<WareSku>(WareSku.class);
        util.exportExcel(response, list, "商品库存数据");
    }

    /**
     * 获取商品库存详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:sku:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wareSkuService.selectWareSkuById(id));
    }

    /**
     * 新增商品库存
     */
    @PreAuthorize("@ss.hasPermi('machaojin:sku:add')")
    @Log(title = "商品库存", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult add(@RequestBody WareSku wareSku)
    {
        return toAjax(wareSkuService.insertWareSku(wareSku));
    }

    /**
     * 修改商品库存
     */
    @PreAuthorize("@ss.hasPermi('machaojin:sku:edit')")
    @Log(title = "商品库存", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody WareSku wareSku)
    {
        return toAjax(wareSkuService.updateWareSku(wareSku));
    }

    /**
     * 删除商品库存
     */
    @PreAuthorize("@ss.hasPermi('machaojin:sku:remove')")
    @Log(title = "商品库存", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(wareSkuService.deleteWareSkuByIds(ids));
    }
}
