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
import com.machaojin.domain.MemberReceiveAddress;
import com.machaojin.service.IMemberReceiveAddressService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 会员收货地址Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/address")
public class MemberReceiveAddressController extends BaseController
{
    @Autowired
    private IMemberReceiveAddressService memberReceiveAddressService;

    /**
     * 查询会员收货地址列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:address:list')")
    @GetMapping("/list")
    public TableDataInfo list(MemberReceiveAddress memberReceiveAddress)
    {
        startPage();
        List<MemberReceiveAddress> list = memberReceiveAddressService.selectMemberReceiveAddressList(memberReceiveAddress);
        return getDataTable(list);
    }

    /**
     * 导出会员收货地址列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:address:export')")
    @Log(title = "会员收货地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemberReceiveAddress memberReceiveAddress)
    {
        List<MemberReceiveAddress> list = memberReceiveAddressService.selectMemberReceiveAddressList(memberReceiveAddress);
        ExcelUtil<MemberReceiveAddress> util = new ExcelUtil<MemberReceiveAddress>(MemberReceiveAddress.class);
        util.exportExcel(response, list, "会员收货地址数据");
    }

    /**
     * 获取会员收货地址详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:address:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(memberReceiveAddressService.selectMemberReceiveAddressById(id));
    }

    /**
     * 新增会员收货地址
     */
    @PreAuthorize("@ss.hasPermi('machaojin:address:add')")
    @Log(title = "会员收货地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MemberReceiveAddress memberReceiveAddress)
    {
        return toAjax(memberReceiveAddressService.insertMemberReceiveAddress(memberReceiveAddress));
    }

    /**
     * 修改会员收货地址
     */
    @PreAuthorize("@ss.hasPermi('machaojin:address:edit')")
    @Log(title = "会员收货地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MemberReceiveAddress memberReceiveAddress)
    {
        return toAjax(memberReceiveAddressService.updateMemberReceiveAddress(memberReceiveAddress));
    }

    /**
     * 删除会员收货地址
     */
    @PreAuthorize("@ss.hasPermi('machaojin:address:remove')")
    @Log(title = "会员收货地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(memberReceiveAddressService.deleteMemberReceiveAddressByIds(ids));
    }
}
