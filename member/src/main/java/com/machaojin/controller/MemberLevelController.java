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
import com.machaojin.domain.MemberLevel;
import com.machaojin.service.IMemberLevelService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 会员等级Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/level")
public class MemberLevelController extends BaseController
{
    @Autowired
    private IMemberLevelService memberLevelService;

    /**
     * 查询会员等级列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:level:list')")
    @GetMapping("/list")
    public TableDataInfo list(MemberLevel memberLevel)
    {
        startPage();
        List<MemberLevel> list = memberLevelService.selectMemberLevelList(memberLevel);
        return getDataTable(list);
    }

    /**
     * 导出会员等级列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:level:export')")
    @Log(title = "会员等级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemberLevel memberLevel)
    {
        List<MemberLevel> list = memberLevelService.selectMemberLevelList(memberLevel);
        ExcelUtil<MemberLevel> util = new ExcelUtil<MemberLevel>(MemberLevel.class);
        util.exportExcel(response, list, "会员等级数据");
    }

    /**
     * 获取会员等级详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:level:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(memberLevelService.selectMemberLevelById(id));
    }

    /**
     * 新增会员等级
     */
    @PreAuthorize("@ss.hasPermi('machaojin:level:add')")
    @Log(title = "会员等级", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult add(@RequestBody MemberLevel memberLevel)
    {
        return toAjax(memberLevelService.insertMemberLevel(memberLevel));
    }

    /**
     * 修改会员等级
     */
    @PreAuthorize("@ss.hasPermi('machaojin:level:edit')")
    @Log(title = "会员等级", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody MemberLevel memberLevel)
    {
        return toAjax(memberLevelService.updateMemberLevel(memberLevel));
    }

    /**
     * 删除会员等级
     */
    @PreAuthorize("@ss.hasPermi('machaojin:level:remove')")
    @Log(title = "会员等级", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(memberLevelService.deleteMemberLevelByIds(ids));
    }
}
