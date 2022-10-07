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
import com.machaojin.domain.SpuImages;
import com.machaojin.service.ISpuImagesService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * spu图片Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/images")
public class SpuImagesController extends BaseController
{
    @Autowired
    private ISpuImagesService spuImagesService;

    /**
     * 查询spu图片列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:images:list')")
    @GetMapping("/list")
    public TableDataInfo list(SpuImages spuImages)
    {
        startPage();
        List<SpuImages> list = spuImagesService.selectSpuImagesList(spuImages);
        return getDataTable(list);
    }

    /**
     * 导出spu图片列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:images:export')")
    @Log(title = "spu图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SpuImages spuImages)
    {
        List<SpuImages> list = spuImagesService.selectSpuImagesList(spuImages);
        ExcelUtil<SpuImages> util = new ExcelUtil<SpuImages>(SpuImages.class);
        util.exportExcel(response, list, "spu图片数据");
    }

    /**
     * 获取spu图片详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:images:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(spuImagesService.selectSpuImagesById(id));
    }

    /**
     * 新增spu图片
     */
    @PreAuthorize("@ss.hasPermi('machaojin:images:add')")
    @Log(title = "spu图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SpuImages spuImages)
    {
        return toAjax(spuImagesService.insertSpuImages(spuImages));
    }

    /**
     * 修改spu图片
     */
    @PreAuthorize("@ss.hasPermi('machaojin:images:edit')")
    @Log(title = "spu图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpuImages spuImages)
    {
        return toAjax(spuImagesService.updateSpuImages(spuImages));
    }

    /**
     * 删除spu图片
     */
    @PreAuthorize("@ss.hasPermi('machaojin:images:remove')")
    @Log(title = "spu图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(spuImagesService.deleteSpuImagesByIds(ids));
    }
}
