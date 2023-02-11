package com.machaojin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;
import javax.servlet.http.HttpServletResponse;


import com.machaojin.service.IBrandService;
import com.machaojin.service.ICategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.machaojin.domain.CategoryBrandRelation;
import com.machaojin.service.ICategoryBrandRelationService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 品牌分类关联Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/relation")
public class CategoryBrandRelationController extends BaseController
{
    @Autowired
    private ICategoryBrandRelationService categoryBrandRelationService;

    /**
     * 查询品牌分类关联列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(CategoryBrandRelation categoryBrandRelation)
    {
        //截取分页查询的条件
        startPage();
        //查询所有的品牌和分类之间的关系
        List<CategoryBrandRelation> list = categoryBrandRelationService.selectCategoryBrandRelationList(categoryBrandRelation);
        return getDataTable(list);
    }

    /**
     * 查询品牌分类关联列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:list')")
    @GetMapping("/brand/list")
    public TableDataInfo brandList(@RequestParam(value = "catId",required = true) Long categoryId)
    {
        //截取分页查询的条件
        startPage();
        //查询所有的品牌和分类之间的关系
        List<CategoryBrandRelation> list = categoryBrandRelationService.selectCategoryBrandRelationByCategoryId(categoryId);
        return getDataTable(list);
    }
    /**
     * 导出品牌分类关联列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:export')")
    @Log(title = "品牌分类关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CategoryBrandRelation categoryBrandRelation)
    {
        List<CategoryBrandRelation> list = categoryBrandRelationService.selectCategoryBrandRelationList(categoryBrandRelation);
        ExcelUtil<CategoryBrandRelation> util = new ExcelUtil<CategoryBrandRelation>(CategoryBrandRelation.class);
        util.exportExcel(response, list, "品牌分类关联数据");
    }

    /**
     * 获取品牌分类关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(categoryBrandRelationService.selectCategoryBrandRelationById(id));
    }

    /**
     * 新增品牌分类关联
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:add')")
    @Log(title = "品牌分类关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody CategoryBrandRelation categoryBrandRelation)
    {
        logger.info("{}",categoryBrandRelation);
        return toAjax(categoryBrandRelationService.insertCategoryBrandRelation(categoryBrandRelation));
    }

    /**
     * 修改品牌分类关联
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:edit')")
    @Log(title = "品牌分类关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CategoryBrandRelation categoryBrandRelation)
    {
        return toAjax(categoryBrandRelationService.updateCategoryBrandRelation(categoryBrandRelation));
    }

    /**
     * 删除品牌分类关联
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:remove')")
    @Log(title = "品牌分类关联", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(categoryBrandRelationService.deleteCategoryBrandRelationByIds(ids));
    }
}
