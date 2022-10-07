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
import com.machaojin.domain.MemberCollectSpu;
import com.machaojin.service.IMemberCollectSpuService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 会员收藏的商品Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/spu")
public class MemberCollectSpuController extends BaseController
{
    @Autowired
    private IMemberCollectSpuService memberCollectSpuService;

    /**
     * 查询会员收藏的商品列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:spu:list')")
    @GetMapping("/list")
    public TableDataInfo list(MemberCollectSpu memberCollectSpu)
    {
        startPage();
        List<MemberCollectSpu> list = memberCollectSpuService.selectMemberCollectSpuList(memberCollectSpu);
        return getDataTable(list);
    }

    /**
     * 导出会员收藏的商品列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:spu:export')")
    @Log(title = "会员收藏的商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemberCollectSpu memberCollectSpu)
    {
        List<MemberCollectSpu> list = memberCollectSpuService.selectMemberCollectSpuList(memberCollectSpu);
        ExcelUtil<MemberCollectSpu> util = new ExcelUtil<MemberCollectSpu>(MemberCollectSpu.class);
        util.exportExcel(response, list, "会员收藏的商品数据");
    }

    /**
     * 获取会员收藏的商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:spu:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(memberCollectSpuService.selectMemberCollectSpuById(id));
    }

    /**
     * 新增会员收藏的商品
     */
    @PreAuthorize("@ss.hasPermi('machaojin:spu:add')")
    @Log(title = "会员收藏的商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MemberCollectSpu memberCollectSpu)
    {
        return toAjax(memberCollectSpuService.insertMemberCollectSpu(memberCollectSpu));
    }

    /**
     * 修改会员收藏的商品
     */
    @PreAuthorize("@ss.hasPermi('machaojin:spu:edit')")
    @Log(title = "会员收藏的商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MemberCollectSpu memberCollectSpu)
    {
        return toAjax(memberCollectSpuService.updateMemberCollectSpu(memberCollectSpu));
    }

    /**
     * 删除会员收藏的商品
     */
    @PreAuthorize("@ss.hasPermi('machaojin:spu:remove')")
    @Log(title = "会员收藏的商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(memberCollectSpuService.deleteMemberCollectSpuByIds(ids));
    }
}
