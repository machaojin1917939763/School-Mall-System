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
import com.machaojin.domain.SeckillSession;
import com.machaojin.service.ISeckillSessionService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 秒杀活动场次Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/session")
public class SeckillSessionController extends BaseController
{
    @Autowired
    private ISeckillSessionService seckillSessionService;

    /**
     * 查询秒杀活动场次列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:session:list')")
    @GetMapping("/list")
    public TableDataInfo list(SeckillSession seckillSession)
    {
        startPage();
        List<SeckillSession> list = seckillSessionService.selectSeckillSessionList(seckillSession);
        return getDataTable(list);
    }

    /**
     * 导出秒杀活动场次列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:session:export')")
    @Log(title = "秒杀活动场次", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SeckillSession seckillSession)
    {
        List<SeckillSession> list = seckillSessionService.selectSeckillSessionList(seckillSession);
        ExcelUtil<SeckillSession> util = new ExcelUtil<SeckillSession>(SeckillSession.class);
        util.exportExcel(response, list, "秒杀活动场次数据");
    }

    /**
     * 获取秒杀活动场次详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:session:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(seckillSessionService.selectSeckillSessionById(id));
    }

    /**
     * 新增秒杀活动场次
     */
    @PreAuthorize("@ss.hasPermi('machaojin:session:add')")
    @Log(title = "秒杀活动场次", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SeckillSession seckillSession)
    {
        return toAjax(seckillSessionService.insertSeckillSession(seckillSession));
    }

    /**
     * 修改秒杀活动场次
     */
    @PreAuthorize("@ss.hasPermi('machaojin:session:edit')")
    @Log(title = "秒杀活动场次", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SeckillSession seckillSession)
    {
        return toAjax(seckillSessionService.updateSeckillSession(seckillSession));
    }

    /**
     * 删除秒杀活动场次
     */
    @PreAuthorize("@ss.hasPermi('machaojin:session:remove')")
    @Log(title = "秒杀活动场次", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(seckillSessionService.deleteSeckillSessionByIds(ids));
    }
}
