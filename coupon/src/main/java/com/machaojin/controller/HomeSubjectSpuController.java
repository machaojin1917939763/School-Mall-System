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
import com.machaojin.domain.HomeSubjectSpu;
import com.machaojin.service.IHomeSubjectSpuService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 专题商品Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/spu")
public class HomeSubjectSpuController extends BaseController
{
    @Autowired
    private IHomeSubjectSpuService homeSubjectSpuService;

    /**
     * 查询专题商品列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:spu:list')")
    @GetMapping("/list")
    public TableDataInfo list(HomeSubjectSpu homeSubjectSpu)
    {
        startPage();
        List<HomeSubjectSpu> list = homeSubjectSpuService.selectHomeSubjectSpuList(homeSubjectSpu);
        return getDataTable(list);
    }

    /**
     * 导出专题商品列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:spu:export')")
    @Log(title = "专题商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HomeSubjectSpu homeSubjectSpu)
    {
        List<HomeSubjectSpu> list = homeSubjectSpuService.selectHomeSubjectSpuList(homeSubjectSpu);
        ExcelUtil<HomeSubjectSpu> util = new ExcelUtil<HomeSubjectSpu>(HomeSubjectSpu.class);
        util.exportExcel(response, list, "专题商品数据");
    }

    /**
     * 获取专题商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:spu:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(homeSubjectSpuService.selectHomeSubjectSpuById(id));
    }

    /**
     * 新增专题商品
     */
    @PreAuthorize("@ss.hasPermi('machaojin:spu:add')")
    @Log(title = "专题商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HomeSubjectSpu homeSubjectSpu)
    {
        return toAjax(homeSubjectSpuService.insertHomeSubjectSpu(homeSubjectSpu));
    }

    /**
     * 修改专题商品
     */
    @PreAuthorize("@ss.hasPermi('machaojin:spu:edit')")
    @Log(title = "专题商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HomeSubjectSpu homeSubjectSpu)
    {
        return toAjax(homeSubjectSpuService.updateHomeSubjectSpu(homeSubjectSpu));
    }

    /**
     * 删除专题商品
     */
    @PreAuthorize("@ss.hasPermi('machaojin:spu:remove')")
    @Log(title = "专题商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(homeSubjectSpuService.deleteHomeSubjectSpuByIds(ids));
    }
}
