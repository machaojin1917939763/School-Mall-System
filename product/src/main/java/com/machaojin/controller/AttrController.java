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
import com.machaojin.domain.Attr;
import com.machaojin.service.IAttrService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 商品属性Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/attr")
public class AttrController extends BaseController
{
    @Autowired
    private IAttrService attrService;

    /**
     * 查询商品属性列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:attr:list')")
    @GetMapping("/list")
    public TableDataInfo list(Attr attr)
    {
        startPage();
        List<Attr> list = attrService.selectAttrList(attr);
        return getDataTable(list);
    }

    /**
     * 导出商品属性列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:attr:export')")
    @Log(title = "商品属性", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Attr attr)
    {
        List<Attr> list = attrService.selectAttrList(attr);
        ExcelUtil<Attr> util = new ExcelUtil<Attr>(Attr.class);
        util.exportExcel(response, list, "商品属性数据");
    }

    /**
     * 获取商品属性详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:attr:query')")
    @GetMapping(value = "/{attrId}")
    public AjaxResult getInfo(@PathVariable("attrId") Long attrId)
    {
        return AjaxResult.success(attrService.selectAttrByAttrId(attrId));
    }

    /**
     * 新增商品属性
     */
    @PreAuthorize("@ss.hasPermi('machaojin:attr:add')")
    @Log(title = "商品属性", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Attr attr)
    {
        return toAjax(attrService.insertAttr(attr));
    }

    /**
     * 修改商品属性
     */
    @PreAuthorize("@ss.hasPermi('machaojin:attr:edit')")
    @Log(title = "商品属性", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Attr attr)
    {
        return toAjax(attrService.updateAttr(attr));
    }

    /**
     * 删除商品属性
     */
    @PreAuthorize("@ss.hasPermi('machaojin:attr:remove')")
    @Log(title = "商品属性", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attrIds}")
    public AjaxResult remove(@PathVariable Long[] attrIds)
    {
        return toAjax(attrService.deleteAttrByAttrIds(attrIds));
    }
}
