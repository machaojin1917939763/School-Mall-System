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
import com.machaojin.domain.GrowthChangeHistory;
import com.machaojin.service.IGrowthChangeHistoryService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 成长值变化历史记录Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/history/gro")
public class GrowthChangeHistoryController extends BaseController
{
    @Autowired
    private IGrowthChangeHistoryService growthChangeHistoryService;

    /**
     * 查询成长值变化历史记录列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(GrowthChangeHistory growthChangeHistory)
    {
        startPage();
        List<GrowthChangeHistory> list = growthChangeHistoryService.selectGrowthChangeHistoryList(growthChangeHistory);
        return getDataTable(list);
    }

    /**
     * 导出成长值变化历史记录列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:history:export')")
    @Log(title = "成长值变化历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GrowthChangeHistory growthChangeHistory)
    {
        List<GrowthChangeHistory> list = growthChangeHistoryService.selectGrowthChangeHistoryList(growthChangeHistory);
        ExcelUtil<GrowthChangeHistory> util = new ExcelUtil<GrowthChangeHistory>(GrowthChangeHistory.class);
        util.exportExcel(response, list, "成长值变化历史记录数据");
    }

    /**
     * 获取成长值变化历史记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:history:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(growthChangeHistoryService.selectGrowthChangeHistoryById(id));
    }

    /**
     * 新增成长值变化历史记录
     */
    @PreAuthorize("@ss.hasPermi('machaojin:history:add')")
    @Log(title = "成长值变化历史记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GrowthChangeHistory growthChangeHistory)
    {
        return toAjax(growthChangeHistoryService.insertGrowthChangeHistory(growthChangeHistory));
    }

    /**
     * 修改成长值变化历史记录
     */
    @PreAuthorize("@ss.hasPermi('machaojin:history:edit')")
    @Log(title = "成长值变化历史记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GrowthChangeHistory growthChangeHistory)
    {
        return toAjax(growthChangeHistoryService.updateGrowthChangeHistory(growthChangeHistory));
    }

    /**
     * 删除成长值变化历史记录
     */
    @PreAuthorize("@ss.hasPermi('machaojin:history:remove')")
    @Log(title = "成长值变化历史记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(growthChangeHistoryService.deleteGrowthChangeHistoryByIds(ids));
    }
}
