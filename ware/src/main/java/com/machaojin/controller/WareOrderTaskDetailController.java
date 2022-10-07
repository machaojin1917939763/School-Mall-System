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
import com.machaojin.domain.WareOrderTaskDetail;
import com.machaojin.service.IWareOrderTaskDetailService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 库存工作单Controller
 * 
 * @author machaojin
 * @date 2022-10-06
 */
@RestController
@RequestMapping("/machaojin/detail")
public class WareOrderTaskDetailController extends BaseController
{
    @Autowired
    private IWareOrderTaskDetailService wareOrderTaskDetailService;

    /**
     * 查询库存工作单列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(WareOrderTaskDetail wareOrderTaskDetail)
    {
        startPage();
        List<WareOrderTaskDetail> list = wareOrderTaskDetailService.selectWareOrderTaskDetailList(wareOrderTaskDetail);
        return getDataTable(list);
    }

    /**
     * 导出库存工作单列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:detail:export')")
    @Log(title = "库存工作单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WareOrderTaskDetail wareOrderTaskDetail)
    {
        List<WareOrderTaskDetail> list = wareOrderTaskDetailService.selectWareOrderTaskDetailList(wareOrderTaskDetail);
        ExcelUtil<WareOrderTaskDetail> util = new ExcelUtil<WareOrderTaskDetail>(WareOrderTaskDetail.class);
        util.exportExcel(response, list, "库存工作单数据");
    }

    /**
     * 获取库存工作单详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wareOrderTaskDetailService.selectWareOrderTaskDetailById(id));
    }

    /**
     * 新增库存工作单
     */
    @PreAuthorize("@ss.hasPermi('machaojin:detail:add')")
    @Log(title = "库存工作单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WareOrderTaskDetail wareOrderTaskDetail)
    {
        return toAjax(wareOrderTaskDetailService.insertWareOrderTaskDetail(wareOrderTaskDetail));
    }

    /**
     * 修改库存工作单
     */
    @PreAuthorize("@ss.hasPermi('machaojin:detail:edit')")
    @Log(title = "库存工作单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WareOrderTaskDetail wareOrderTaskDetail)
    {
        return toAjax(wareOrderTaskDetailService.updateWareOrderTaskDetail(wareOrderTaskDetail));
    }

    /**
     * 删除库存工作单
     */
    @PreAuthorize("@ss.hasPermi('machaojin:detail:remove')")
    @Log(title = "库存工作单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wareOrderTaskDetailService.deleteWareOrderTaskDetailByIds(ids));
    }
}
