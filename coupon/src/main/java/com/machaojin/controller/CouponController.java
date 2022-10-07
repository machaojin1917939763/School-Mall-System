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
import com.machaojin.domain.Coupon;
import com.machaojin.service.ICouponService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 优惠券信息Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/coupon")
public class CouponController extends BaseController
{
    @Autowired
    private ICouponService couponService;

    /**
     * 查询优惠券信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:coupon:list')")
    @GetMapping("/list")
    public TableDataInfo list(Coupon coupon)
    {
        startPage();
        List<Coupon> list = couponService.selectCouponList(coupon);
        return getDataTable(list);
    }

    /**
     * 导出优惠券信息列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:coupon:export')")
    @Log(title = "优惠券信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Coupon coupon)
    {
        List<Coupon> list = couponService.selectCouponList(coupon);
        ExcelUtil<Coupon> util = new ExcelUtil<Coupon>(Coupon.class);
        util.exportExcel(response, list, "优惠券信息数据");
    }

    /**
     * 获取优惠券信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:coupon:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(couponService.selectCouponById(id));
    }

    /**
     * 新增优惠券信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:coupon:add')")
    @Log(title = "优惠券信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Coupon coupon)
    {
        return toAjax(couponService.insertCoupon(coupon));
    }

    /**
     * 修改优惠券信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:coupon:edit')")
    @Log(title = "优惠券信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Coupon coupon)
    {
        return toAjax(couponService.updateCoupon(coupon));
    }

    /**
     * 删除优惠券信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:coupon:remove')")
    @Log(title = "优惠券信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(couponService.deleteCouponByIds(ids));
    }
}
