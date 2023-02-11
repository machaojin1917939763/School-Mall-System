package com.machaojin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.machaojin.dto.SkuReductionTo;
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
import com.machaojin.domain.SkuFullReduction;
import com.machaojin.service.ISkuFullReductionService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 商品满减信息Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/reduction")
public class SkuFullReductionController extends BaseController
{
    @Autowired
    private ISkuFullReductionService skuFullReductionService;

    /**
     * 查询商品满减信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:reduction:list')")
    @GetMapping("/list")
    public TableDataInfo list(SkuFullReduction skuFullReduction)
    {
        startPage();
        List<SkuFullReduction> list = skuFullReductionService.selectSkuFullReductionList(skuFullReduction);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('machaojin:reduction:insert')")
    @Log(title = "商品满减信息", businessType = BusinessType.INSERT)
    @PostMapping("/save/info")
    AjaxResult saveReduction(@RequestBody SkuReductionTo skuReductionTo){
        return AjaxResult.success(skuFullReductionService.saveReductions(skuReductionTo));
    }

    /**
     * 导出商品满减信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:reduction:export')")
    @Log(title = "商品满减信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SkuFullReduction skuFullReduction)
    {
        List<SkuFullReduction> list = skuFullReductionService.selectSkuFullReductionList(skuFullReduction);
        ExcelUtil<SkuFullReduction> util = new ExcelUtil<SkuFullReduction>(SkuFullReduction.class);
        util.exportExcel(response, list, "商品满减信息数据");
    }

    /**
     * 获取商品满减信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:reduction:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(skuFullReductionService.selectSkuFullReductionById(id));
    }

    /**
     * 新增商品满减信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:reduction:add')")
    @Log(title = "商品满减信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SkuFullReduction skuFullReduction)
    {
        return toAjax(skuFullReductionService.insertSkuFullReduction(skuFullReduction));
    }

    /**
     * 修改商品满减信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:reduction:edit')")
    @Log(title = "商品满减信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SkuFullReduction skuFullReduction)
    {
        return toAjax(skuFullReductionService.updateSkuFullReduction(skuFullReduction));
    }

    /**
     * 删除商品满减信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:reduction:remove')")
    @Log(title = "商品满减信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(skuFullReductionService.deleteSkuFullReductionByIds(ids));
    }
}
