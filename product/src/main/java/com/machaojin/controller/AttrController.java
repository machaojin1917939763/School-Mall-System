package com.machaojin.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machaojin.vo.AttrRelationVo;
import com.machaojin.vo.AttrReqVo;
import com.machaojin.vo.AttrVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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
@Transactional(rollbackFor = Exception.class)
public class AttrController extends BaseController
{
    @Autowired
    private IAttrService attrService;

    /**
     * 查询商品属性列表
     * http://localhost:8087/api/product/machaojin/attr/base/list/0?t=1669022028620&page=1&limit=10&key=
     * http://localhost:8087/api/product/machaojin/attr/sale/list/0?t=1669366285407&page=1&limit=10&key=
     * t:1669290245940
     * page:1
     * limit:10
     * key:
     */
    @PreAuthorize("@ss.hasPermi('machaojin:attr:list')")
    @GetMapping("/list/{id}")
    public AjaxResult list(@PathVariable String id)
    {
        startPage();
        AttrReqVo attr = attrService.selectAttrByAttrId(Long.valueOf(id));
        return AjaxResult.success(attr);
    }

    @PreAuthorize("@ss.hasPermi('machaojin:attr:list')")
    @GetMapping("/{type}/list/{id}")
    public AjaxResult list(@PathVariable String id, @RequestParam Map<String,String> params, @PathVariable String type)
    {
        Page<Attr> page = new Page<Attr>(Long.parseLong(params.get("page")),Long.parseLong(params.get("limit")));

        return AjaxResult.success(attrService.selectAttrList(page,params,id,type));
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
    @GetMapping(value = "/info/{attrId}")
    public AjaxResult getInfo(@PathVariable("attrId") Long attrId)
    {
        return AjaxResult.success(attrService.selectAttrByAttrId(attrId));
    }

    /**
     * 新增商品属性
     */
    @PreAuthorize("@ss.hasPermi('machaojin:attr:add')")
    @Log(title = "商品属性", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult add(@RequestBody AttrVo attr)
    {
        return toAjax(attrService.insertAttrVo(attr));
    }

    /**
     * 修改商品属性
     */
    @PreAuthorize("@ss.hasPermi('machaojin:attr:edit')")
    @Log(title = "商品属性", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody AttrVo attr)
    {
        return toAjax(attrService.updateAttr(attr));
    }

    /**
     * 删除商品属性
     */
    @PreAuthorize("@ss.hasPermi('machaojin:attr:remove')")
    @Log(title = "商品属性", businessType = BusinessType.DELETE)
	@DeleteMapping("/delete/{attrIds}")
    public AjaxResult remove(@PathVariable Long[] attrIds)
    {
        return toAjax(attrService.deleteAttrByAttrIds(attrIds));
    }
    /**
     * 删除商品属性
     */
    @PreAuthorize("@ss.hasPermi('machaojin:attr:remove')")
    @Log(title = "商品属性", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult removeAll(@RequestBody Long[] attrIds)
    {
        return toAjax(attrService.deleteAttrByAttrIds(attrIds));
    }
}
