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
import com.machaojin.domain.MemberCollectSubject;
import com.machaojin.service.IMemberCollectSubjectService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 会员收藏的专题活动Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/subject")
public class MemberCollectSubjectController extends BaseController
{
    @Autowired
    private IMemberCollectSubjectService memberCollectSubjectService;

    /**
     * 查询会员收藏的专题活动列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:subject:list')")
    @GetMapping("/list")
    public TableDataInfo list(MemberCollectSubject memberCollectSubject)
    {
        startPage();
        List<MemberCollectSubject> list = memberCollectSubjectService.selectMemberCollectSubjectList(memberCollectSubject);
        return getDataTable(list);
    }

    /**
     * 导出会员收藏的专题活动列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:subject:export')")
    @Log(title = "会员收藏的专题活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemberCollectSubject memberCollectSubject)
    {
        List<MemberCollectSubject> list = memberCollectSubjectService.selectMemberCollectSubjectList(memberCollectSubject);
        ExcelUtil<MemberCollectSubject> util = new ExcelUtil<MemberCollectSubject>(MemberCollectSubject.class);
        util.exportExcel(response, list, "会员收藏的专题活动数据");
    }

    /**
     * 获取会员收藏的专题活动详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:subject:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(memberCollectSubjectService.selectMemberCollectSubjectById(id));
    }

    /**
     * 新增会员收藏的专题活动
     */
    @PreAuthorize("@ss.hasPermi('machaojin:subject:add')")
    @Log(title = "会员收藏的专题活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MemberCollectSubject memberCollectSubject)
    {
        return toAjax(memberCollectSubjectService.insertMemberCollectSubject(memberCollectSubject));
    }

    /**
     * 修改会员收藏的专题活动
     */
    @PreAuthorize("@ss.hasPermi('machaojin:subject:edit')")
    @Log(title = "会员收藏的专题活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MemberCollectSubject memberCollectSubject)
    {
        return toAjax(memberCollectSubjectService.updateMemberCollectSubject(memberCollectSubject));
    }

    /**
     * 删除会员收藏的专题活动
     */
    @PreAuthorize("@ss.hasPermi('machaojin:subject:remove')")
    @Log(title = "会员收藏的专题活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(memberCollectSubjectService.deleteMemberCollectSubjectByIds(ids));
    }
}
