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
import com.machaojin.domain.ProductAttrValue;
import com.machaojin.service.IProductAttrValueService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * spu属性值Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/product/attr/value")
public class ProductAttrValueController extends BaseController
{
    @Autowired
    private IProductAttrValueService productAttrValueService;

    /**
     * 查询spu属性值列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:value:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductAttrValue productAttrValue)
    {
        startPage();
        List<ProductAttrValue> list = productAttrValueService.selectProductAttrValueList(productAttrValue);
        return getDataTable(list);
    }

    /**
     * 导出spu属性值列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:value:export')")
    @Log(title = "spu属性值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductAttrValue productAttrValue)
    {
        List<ProductAttrValue> list = productAttrValueService.selectProductAttrValueList(productAttrValue);
        ExcelUtil<ProductAttrValue> util = new ExcelUtil<ProductAttrValue>(ProductAttrValue.class);
        util.exportExcel(response, list, "spu属性值数据");
    }

    /**
     * 获取spu属性值详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:value:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(productAttrValueService.selectProductAttrValueById(id));
    }

    /**
     * 新增spu属性值
     */
    @PreAuthorize("@ss.hasPermi('machaojin:value:add')")
    @Log(title = "spu属性值", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult add(@RequestBody ProductAttrValue productAttrValue)
    {
        return toAjax(productAttrValueService.insertProductAttrValue(productAttrValue));
    }

    /**
     * 修改spu属性值
     */
    @PreAuthorize("@ss.hasPermi('machaojin:value:edit')")
    @Log(title = "spu属性值", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody ProductAttrValue productAttrValue)
    {
        return toAjax(productAttrValueService.updateProductAttrValue(productAttrValue));
    }

    /**
     * 修改spu属性值
     */
    @PreAuthorize("@ss.hasPermi('machaojin:value:edit')")
    @Log(title = "spu属性值", businessType = BusinessType.UPDATE)
    @PostMapping("/update/{spuId}")
    public AjaxResult editBySpuId(@RequestBody List<ProductAttrValue> productAttrValues, @PathVariable Integer spuId)
    {
        return toAjax(productAttrValueService.updateProductAttrValues(productAttrValues,spuId));
    }

    /**
     * 删除spu属性值
     */
    @PreAuthorize("@ss.hasPermi('machaojin:value:remove')")
    @Log(title = "spu属性值", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(productAttrValueService.deleteProductAttrValueByIds(ids));
    }
}
