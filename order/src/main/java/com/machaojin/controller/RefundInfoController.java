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
import com.machaojin.domain.RefundInfo;
import com.machaojin.service.IRefundInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 退款信息Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/info")
public class RefundInfoController extends BaseController
{
    @Autowired
    private IRefundInfoService refundInfoService;

    /**
     * 查询退款信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(RefundInfo refundInfo)
    {
        startPage();
        List<RefundInfo> list = refundInfoService.selectRefundInfoList(refundInfo);
        return getDataTable(list);
    }

    /**
     * 导出退款信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:export')")
    @Log(title = "退款信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RefundInfo refundInfo)
    {
        List<RefundInfo> list = refundInfoService.selectRefundInfoList(refundInfo);
        ExcelUtil<RefundInfo> util = new ExcelUtil<RefundInfo>(RefundInfo.class);
        util.exportExcel(response, list, "退款信息数据");
    }

    /**
     * 获取退款信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(refundInfoService.selectRefundInfoById(id));
    }

    /**
     * 新增退款信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:add')")
    @Log(title = "退款信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RefundInfo refundInfo)
    {
        return toAjax(refundInfoService.insertRefundInfo(refundInfo));
    }

    /**
     * 修改退款信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:edit')")
    @Log(title = "退款信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RefundInfo refundInfo)
    {
        return toAjax(refundInfoService.updateRefundInfo(refundInfo));
    }

    /**
     * 删除退款信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:remove')")
    @Log(title = "退款信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(refundInfoService.deleteRefundInfoByIds(ids));
    }
}
