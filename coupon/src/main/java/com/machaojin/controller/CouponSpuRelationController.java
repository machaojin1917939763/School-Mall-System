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
import com.machaojin.domain.CouponSpuRelation;
import com.machaojin.service.ICouponSpuRelationService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 优惠券与产品关联Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/relation/cou")
public class CouponSpuRelationController extends BaseController
{
    @Autowired
    private ICouponSpuRelationService couponSpuRelationService;

    /**
     * 查询优惠券与产品关联列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(CouponSpuRelation couponSpuRelation)
    {
        startPage();
        List<CouponSpuRelation> list = couponSpuRelationService.selectCouponSpuRelationList(couponSpuRelation);
        return getDataTable(list);
    }

    /**
     * 导出优惠券与产品关联列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:export')")
    @Log(title = "优惠券与产品关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CouponSpuRelation couponSpuRelation)
    {
        List<CouponSpuRelation> list = couponSpuRelationService.selectCouponSpuRelationList(couponSpuRelation);
        ExcelUtil<CouponSpuRelation> util = new ExcelUtil<CouponSpuRelation>(CouponSpuRelation.class);
        util.exportExcel(response, list, "优惠券与产品关联数据");
    }

    /**
     * 获取优惠券与产品关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(couponSpuRelationService.selectCouponSpuRelationById(id));
    }

    /**
     * 新增优惠券与产品关联
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:add')")
    @Log(title = "优惠券与产品关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CouponSpuRelation couponSpuRelation)
    {
        return toAjax(couponSpuRelationService.insertCouponSpuRelation(couponSpuRelation));
    }

    /**
     * 修改优惠券与产品关联
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:edit')")
    @Log(title = "优惠券与产品关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CouponSpuRelation couponSpuRelation)
    {
        return toAjax(couponSpuRelationService.updateCouponSpuRelation(couponSpuRelation));
    }

    /**
     * 删除优惠券与产品关联
     */
    @PreAuthorize("@ss.hasPermi('machaojin:relation:remove')")
    @Log(title = "优惠券与产品关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(couponSpuRelationService.deleteCouponSpuRelationByIds(ids));
    }
}
