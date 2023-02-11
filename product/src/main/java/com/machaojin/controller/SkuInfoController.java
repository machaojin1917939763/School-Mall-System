package com.machaojin.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.machaojin.vo.SkuInfoVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.machaojin.domain.SkuInfo;
import com.machaojin.service.ISkuInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * sku信息Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/sku/info")
public class SkuInfoController extends BaseController
{
    @Autowired
    private ISkuInfoService skuInfoService;

    /**
     * 查询sku信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(SkuInfoVo skuInfo, @RequestParam(required = false) String key)
    {
        startPage();
        logger.info(skuInfo.toString());
        skuInfo.setCatalogId(skuInfo.getCatalogId() == 0 || skuInfo.getCatalogId() == null ? null : skuInfo.getCatalogId());
        skuInfo.setBrandId(skuInfo.getBrandId() == 0 || skuInfo.getBrandId() == null ? null : skuInfo.getBrandId());
        skuInfo.setSkuName(skuInfo.getSkuName() != null ? skuInfo.getSkuName() : key);
        skuInfo.setMin(skuInfo.getMin() == 0 || skuInfo.getMin() ==  null? null : skuInfo.getMin());
        skuInfo.setMax(skuInfo.getMax() == 0 || skuInfo.getMax() == null? null : skuInfo.getMax());
        List<SkuInfo> list = skuInfoService.selectSkuInfoList(skuInfo);
        return getDataTable(list);
    }

    /**
     * 导出sku信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:export')")
    @Log(title = "sku信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SkuInfoVo skuInfo)
    {
        List<SkuInfo> list = skuInfoService.selectSkuInfoList(skuInfo);
        ExcelUtil<SkuInfo> util = new ExcelUtil<SkuInfo>(SkuInfo.class);
        util.exportExcel(response, list, "sku信息数据");
    }

    /**
     * 获取sku信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:query')")
    @GetMapping(value = "/{skuId}")
    public AjaxResult getInfo(@PathVariable("skuId") Long skuId)
    {
        return AjaxResult.success(skuInfoService.selectSkuInfoBySkuId(skuId));
    }

    /**
     * 新增sku信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:add')")
    @Log(title = "sku信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SkuInfo skuInfo)
    {
        return toAjax(skuInfoService.insertSkuInfo(skuInfo));
    }

    /**
     * 修改sku信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:edit')")
    @Log(title = "sku信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SkuInfo skuInfo)
    {
        return toAjax(skuInfoService.updateSkuInfo(skuInfo));
    }

    /**
     * 删除sku信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:remove')")
    @Log(title = "sku信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{skuIds}")
    public AjaxResult remove(@PathVariable Long[] skuIds)
    {
        return toAjax(skuInfoService.deleteSkuInfoBySkuIds(skuIds));
    }
}
