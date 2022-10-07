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
import com.machaojin.domain.SpuComment;
import com.machaojin.service.ISpuCommentService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 商品评价Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/comment")
public class SpuCommentController extends BaseController
{
    @Autowired
    private ISpuCommentService spuCommentService;

    /**
     * 查询商品评价列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(SpuComment spuComment)
    {
        startPage();
        List<SpuComment> list = spuCommentService.selectSpuCommentList(spuComment);
        return getDataTable(list);
    }

    /**
     * 导出商品评价列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:comment:export')")
    @Log(title = "商品评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SpuComment spuComment)
    {
        List<SpuComment> list = spuCommentService.selectSpuCommentList(spuComment);
        ExcelUtil<SpuComment> util = new ExcelUtil<SpuComment>(SpuComment.class);
        util.exportExcel(response, list, "商品评价数据");
    }

    /**
     * 获取商品评价详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(spuCommentService.selectSpuCommentById(id));
    }

    /**
     * 新增商品评价
     */
    @PreAuthorize("@ss.hasPermi('machaojin:comment:add')")
    @Log(title = "商品评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SpuComment spuComment)
    {
        return toAjax(spuCommentService.insertSpuComment(spuComment));
    }

    /**
     * 修改商品评价
     */
    @PreAuthorize("@ss.hasPermi('machaojin:comment:edit')")
    @Log(title = "商品评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpuComment spuComment)
    {
        return toAjax(spuCommentService.updateSpuComment(spuComment));
    }

    /**
     * 删除商品评价
     */
    @PreAuthorize("@ss.hasPermi('machaojin:comment:remove')")
    @Log(title = "商品评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(spuCommentService.deleteSpuCommentByIds(ids));
    }
}
