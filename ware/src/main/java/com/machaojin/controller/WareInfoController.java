package com.machaojin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.machaojin.domain.WareInfo;
import com.machaojin.service.IWareInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 仓库信息Controller
 * 
 * @author machaojin
 * @date 2022-10-06
 */
@RestController
@RequestMapping("/machaojin/ware/info")
public class WareInfoController extends BaseController
{
    @Autowired
    private IWareInfoService wareInfoService;

    /**
     * 查询仓库信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(WareInfo wareInfo)
    {
        startPage();
        List<WareInfo> list = wareInfoService.selectWareInfoList(wareInfo);
        return getDataTable(list);
    }

    /**
     * 导出仓库信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:export')")
    @Log(title = "仓库信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WareInfo wareInfo)
    {
        List<WareInfo> list = wareInfoService.selectWareInfoList(wareInfo);
        ExcelUtil<WareInfo> util = new ExcelUtil<WareInfo>(WareInfo.class);
        util.exportExcel(response, list, "仓库信息数据");
    }

    /**
     * 获取仓库信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wareInfoService.selectWareInfoById(id));
    }

    /**
     * 新增仓库信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:add')")
    @Log(title = "仓库信息", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult add(@RequestBody WareInfo wareInfo)
    {
        return toAjax(wareInfoService.insertWareInfo(wareInfo));
    }

    /**
     * 修改仓库信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:edit')")
    @Log(title = "仓库信息", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody WareInfo wareInfo)
    {
        return toAjax(wareInfoService.updateWareInfo(wareInfo));
    }

    /**
     * 删除仓库信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:remove')")
    @Log(title = "仓库信息", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(wareInfoService.deleteWareInfoByIds(ids));
    }
}
