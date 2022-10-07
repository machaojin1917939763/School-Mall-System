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
import com.machaojin.domain.MemberStatisticsInfo;
import com.machaojin.service.IMemberStatisticsInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 会员统计信息Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/info")
public class MemberStatisticsInfoController extends BaseController
{
    @Autowired
    private IMemberStatisticsInfoService memberStatisticsInfoService;

    /**
     * 查询会员统计信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(MemberStatisticsInfo memberStatisticsInfo)
    {
        startPage();
        List<MemberStatisticsInfo> list = memberStatisticsInfoService.selectMemberStatisticsInfoList(memberStatisticsInfo);
        return getDataTable(list);
    }

    /**
     * 导出会员统计信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:export')")
    @Log(title = "会员统计信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemberStatisticsInfo memberStatisticsInfo)
    {
        List<MemberStatisticsInfo> list = memberStatisticsInfoService.selectMemberStatisticsInfoList(memberStatisticsInfo);
        ExcelUtil<MemberStatisticsInfo> util = new ExcelUtil<MemberStatisticsInfo>(MemberStatisticsInfo.class);
        util.exportExcel(response, list, "会员统计信息数据");
    }

    /**
     * 获取会员统计信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(memberStatisticsInfoService.selectMemberStatisticsInfoById(id));
    }

    /**
     * 新增会员统计信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:add')")
    @Log(title = "会员统计信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MemberStatisticsInfo memberStatisticsInfo)
    {
        return toAjax(memberStatisticsInfoService.insertMemberStatisticsInfo(memberStatisticsInfo));
    }

    /**
     * 修改会员统计信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:edit')")
    @Log(title = "会员统计信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MemberStatisticsInfo memberStatisticsInfo)
    {
        return toAjax(memberStatisticsInfoService.updateMemberStatisticsInfo(memberStatisticsInfo));
    }

    /**
     * 删除会员统计信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:remove')")
    @Log(title = "会员统计信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(memberStatisticsInfoService.deleteMemberStatisticsInfoByIds(ids));
    }
}
