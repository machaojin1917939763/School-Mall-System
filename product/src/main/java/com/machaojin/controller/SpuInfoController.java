package com.machaojin.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.machaojin.domain.*;
import com.machaojin.service.*;
import com.machaojin.vo.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * spu信息Controller
 *
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/spu/info")
public class SpuInfoController extends BaseController
{
    @Autowired
    private ISpuInfoService spuInfoService;

    /**
     * 查询spu信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(SpuInfo spuInfo,@RequestParam(required = false) String key,@RequestParam(required = false) Integer status)
    {
        startPage();
        if (status != null){
            spuInfo.setPublishStatus(status);
        }
        List<SpuInfo> list = spuInfoService.selectSpuInfoList(spuInfo,key);
        return getDataTable(list);
    }

    /**
     * 导出spu信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:export')")
    @Log(title = "spu信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SpuInfo spuInfo)
    {
        List<SpuInfo> list = spuInfoService.selectSpuInfoList(spuInfo, null);
        ExcelUtil<SpuInfo> util = new ExcelUtil<SpuInfo>(SpuInfo.class);
        util.exportExcel(response, list, "spu信息数据");
    }

    /**
     * 获取spu信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(spuInfoService.selectSpuInfoById(id));
    }

    /**
     * 新增spu信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:add')")
    @Log(title = "spu信息", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult add(@RequestBody SpuSaveVo spuSaveVo)
    {
        return toAjax(spuInfoService.saveProduct(spuSaveVo));
    }

    /**
     * 修改spu信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:edit')")
    @Log(title = "spu信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpuInfo spuInfo)
    {
        return toAjax(spuInfoService.updateSpuInfo(spuInfo));
    }

    /**
     * 商品上架
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:edit')")
    @Log(title = "spu信息", businessType = BusinessType.UPDATE)
    @PostMapping("/{spuId}/up")
    public AjaxResult up(@PathVariable Long spuId)
    {
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.setUpdateTime(new Date());
        spuInfo.setPublishStatus(1);
        //TODO
        spuInfo.setId(spuId);
        return toAjax(spuInfoService.updateSpuInfo(spuInfo));
    }

    /**
     * 删除spu信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:info:remove')")
    @Log(title = "spu信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(spuInfoService.deleteSpuInfoByIds(ids));
    }
}
