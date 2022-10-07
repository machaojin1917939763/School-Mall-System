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
import com.machaojin.domain.HomeAdv;
import com.machaojin.service.IHomeAdvService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 首页轮播广告Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/adv")
public class HomeAdvController extends BaseController
{
    @Autowired
    private IHomeAdvService homeAdvService;

    /**
     * 查询首页轮播广告列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:adv:list')")
    @GetMapping("/list")
    public TableDataInfo list(HomeAdv homeAdv)
    {
        startPage();
        List<HomeAdv> list = homeAdvService.selectHomeAdvList(homeAdv);
        return getDataTable(list);
    }

    /**
     * 导出首页轮播广告列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:adv:export')")
    @Log(title = "首页轮播广告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HomeAdv homeAdv)
    {
        List<HomeAdv> list = homeAdvService.selectHomeAdvList(homeAdv);
        ExcelUtil<HomeAdv> util = new ExcelUtil<HomeAdv>(HomeAdv.class);
        util.exportExcel(response, list, "首页轮播广告数据");
    }

    /**
     * 获取首页轮播广告详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:adv:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(homeAdvService.selectHomeAdvById(id));
    }

    /**
     * 新增首页轮播广告
     */
    @PreAuthorize("@ss.hasPermi('machaojin:adv:add')")
    @Log(title = "首页轮播广告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HomeAdv homeAdv)
    {
        return toAjax(homeAdvService.insertHomeAdv(homeAdv));
    }

    /**
     * 修改首页轮播广告
     */
    @PreAuthorize("@ss.hasPermi('machaojin:adv:edit')")
    @Log(title = "首页轮播广告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HomeAdv homeAdv)
    {
        return toAjax(homeAdvService.updateHomeAdv(homeAdv));
    }

    /**
     * 删除首页轮播广告
     */
    @PreAuthorize("@ss.hasPermi('machaojin:adv:remove')")
    @Log(title = "首页轮播广告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(homeAdvService.deleteHomeAdvByIds(ids));
    }
}
