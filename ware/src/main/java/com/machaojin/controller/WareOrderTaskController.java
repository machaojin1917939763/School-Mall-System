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
import com.machaojin.domain.WareOrderTask;
import com.machaojin.service.IWareOrderTaskService;
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
@RequestMapping("/machaojin/task")
public class WareOrderTaskController extends BaseController
{
    @Autowired
    private IWareOrderTaskService wareOrderTaskService;

    /**
     * 查询库存工作单列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(WareOrderTask wareOrderTask)
    {
        startPage();
        List<WareOrderTask> list = wareOrderTaskService.selectWareOrderTaskList(wareOrderTask);
        return getDataTable(list);
    }

    /**
     * 导出库存工作单列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:task:export')")
    @Log(title = "库存工作单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WareOrderTask wareOrderTask)
    {
        List<WareOrderTask> list = wareOrderTaskService.selectWareOrderTaskList(wareOrderTask);
        ExcelUtil<WareOrderTask> util = new ExcelUtil<WareOrderTask>(WareOrderTask.class);
        util.exportExcel(response, list, "库存工作单数据");
    }

    /**
     * 获取库存工作单详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wareOrderTaskService.selectWareOrderTaskById(id));
    }

    /**
     * 新增库存工作单
     */
    @PreAuthorize("@ss.hasPermi('machaojin:task:add')")
    @Log(title = "库存工作单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WareOrderTask wareOrderTask)
    {
        return toAjax(wareOrderTaskService.insertWareOrderTask(wareOrderTask));
    }

    /**
     * 修改库存工作单
     */
    @PreAuthorize("@ss.hasPermi('machaojin:task:edit')")
    @Log(title = "库存工作单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WareOrderTask wareOrderTask)
    {
        return toAjax(wareOrderTaskService.updateWareOrderTask(wareOrderTask));
    }

    /**
     * 删除库存工作单
     */
    @PreAuthorize("@ss.hasPermi('machaojin:task:remove')")
    @Log(title = "库存工作单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wareOrderTaskService.deleteWareOrderTaskByIds(ids));
    }
}
