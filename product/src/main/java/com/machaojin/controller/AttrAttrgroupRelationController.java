package com.machaojin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.machaojin.vo.AttrRelationVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.machaojin.domain.AttrAttrgroupRelation;
import com.machaojin.service.IAttrAttrgroupRelationService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 属性&属性分组关联Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/relation/attr")
@Transactional(rollbackFor = Exception.class)
public class AttrAttrgroupRelationController extends BaseController
{
    @Autowired
    private IAttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * 查询属性&属性分组关联列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(AttrAttrgroupRelation attrAttrgroupRelation)
    {
        startPage();
        List<AttrAttrgroupRelation> list = attrAttrgroupRelationService.selectAttrAttrgroupRelationList(attrAttrgroupRelation);
        return getDataTable(list);
    }

    /**
     * 导出属性&属性分组关联列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:export')")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AttrAttrgroupRelation attrAttrgroupRelation)
    {
        List<AttrAttrgroupRelation> list = attrAttrgroupRelationService.selectAttrAttrgroupRelationList(attrAttrgroupRelation);
        ExcelUtil<AttrAttrgroupRelation> util = new ExcelUtil<AttrAttrgroupRelation>(AttrAttrgroupRelation.class);
        util.exportExcel(response, list, "属性&属性分组关联数据");
    }

    /**
     * 获取属性&属性分组关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(attrAttrgroupRelationService.selectAttrAttrgroupRelationById(id));
    }

    /**
     * 新增属性&属性分组关联
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:add')")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult add(@RequestBody List<AttrAttrgroupRelation> attrAttrgroupRelations)
    {
        return toAjax(attrAttrgroupRelationService.insertAttrAttrgroupRelation(attrAttrgroupRelations));
    }

    /**
     * 修改属性&属性分组关联
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:edit')")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AttrAttrgroupRelation attrAttrgroupRelation)
    {
        return toAjax(attrAttrgroupRelationService.updateAttrAttrgroupRelation(attrAttrgroupRelation));
    }

    /**
     * 删除属性&属性分组关联
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:remove')")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(attrAttrgroupRelationService.deleteAttrAttrgroupRelationByIds(ids));
    }
}
