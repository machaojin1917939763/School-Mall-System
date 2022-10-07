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
import com.machaojin.domain.SpuBounds;
import com.machaojin.service.ISpuBoundsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 商品spu积分设置Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/bounds")
public class SpuBoundsController extends BaseController
{
    @Autowired
    private ISpuBoundsService spuBoundsService;

    /**
     * 查询商品spu积分设置列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:bounds:list')")
    @GetMapping("/list")
    public TableDataInfo list(SpuBounds spuBounds)
    {
        startPage();
        List<SpuBounds> list = spuBoundsService.selectSpuBoundsList(spuBounds);
        return getDataTable(list);
    }

    /**
     * 导出商品spu积分设置列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:bounds:export')")
    @Log(title = "商品spu积分设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SpuBounds spuBounds)
    {
        List<SpuBounds> list = spuBoundsService.selectSpuBoundsList(spuBounds);
        ExcelUtil<SpuBounds> util = new ExcelUtil<SpuBounds>(SpuBounds.class);
        util.exportExcel(response, list, "商品spu积分设置数据");
    }

    /**
     * 获取商品spu积分设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:bounds:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(spuBoundsService.selectSpuBoundsById(id));
    }

    /**
     * 新增商品spu积分设置
     */
    @PreAuthorize("@ss.hasPermi('machaojin:bounds:add')")
    @Log(title = "商品spu积分设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SpuBounds spuBounds)
    {
        return toAjax(spuBoundsService.insertSpuBounds(spuBounds));
    }

    /**
     * 修改商品spu积分设置
     */
    @PreAuthorize("@ss.hasPermi('machaojin:bounds:edit')")
    @Log(title = "商品spu积分设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpuBounds spuBounds)
    {
        return toAjax(spuBoundsService.updateSpuBounds(spuBounds));
    }

    /**
     * 删除商品spu积分设置
     */
    @PreAuthorize("@ss.hasPermi('machaojin:bounds:remove')")
    @Log(title = "商品spu积分设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(spuBoundsService.deleteSpuBoundsByIds(ids));
    }
}
