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
import com.machaojin.domain.SkuImages;
import com.machaojin.service.ISkuImagesService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * sku图片Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/images/sku")
public class SkuImagesController extends BaseController
{
    @Autowired
    private ISkuImagesService skuImagesService;

    /**
     * 查询sku图片列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:images:list')")
    @GetMapping("/list")
    public TableDataInfo list(SkuImages skuImages)
    {
        startPage();
        List<SkuImages> list = skuImagesService.selectSkuImagesList(skuImages);
        return getDataTable(list);
    }

    /**
     * 导出sku图片列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:images:export')")
    @Log(title = "sku图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SkuImages skuImages)
    {
        List<SkuImages> list = skuImagesService.selectSkuImagesList(skuImages);
        ExcelUtil<SkuImages> util = new ExcelUtil<SkuImages>(SkuImages.class);
        util.exportExcel(response, list, "sku图片数据");
    }

    /**
     * 获取sku图片详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:images:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(skuImagesService.selectSkuImagesById(id));
    }

    /**
     * 新增sku图片
     */
    @PreAuthorize("@ss.hasPermi('machaojin:images:add')")
    @Log(title = "sku图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SkuImages skuImages)
    {
        return toAjax(skuImagesService.insertSkuImages(skuImages));
    }

    /**
     * 修改sku图片
     */
    @PreAuthorize("@ss.hasPermi('machaojin:images:edit')")
    @Log(title = "sku图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SkuImages skuImages)
    {
        return toAjax(skuImagesService.updateSkuImages(skuImages));
    }

    /**
     * 删除sku图片
     */
    @PreAuthorize("@ss.hasPermi('machaojin:images:remove')")
    @Log(title = "sku图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(skuImagesService.deleteSkuImagesByIds(ids));
    }
}
