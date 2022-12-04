package com.machaojin.controller;

import java.util.List;
import java.util.Map;
import javax.management.ObjectName;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machaojin.domain.Attr;
import com.machaojin.service.impl.AttrServiceImpl;
import com.machaojin.vo.AttrRelationVo;
import com.ruoyi.common.constant.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.machaojin.domain.AttrGroup;
import com.machaojin.service.IAttrGroupService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 属性分组Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/group")
public class AttrGroupController extends BaseController
{
    @Autowired
    private IAttrGroupService attrGroupService;

    @Autowired
    private AttrServiceImpl attrService;

    /**
     * 查询属性分组列表
     * @param params {
     *    page: 1,//当前页码
     *    limit: 10,//每页记录数
     *    sidx: 'id',//排序字段
     *    order: 'asc/desc',//排序方式
     *    key: '华为'//检索关键字
     * }
     * @param  catalogId id
     * @return 属性分组集合
     */
//    @PreAuthorize("@ss.hasPermi('machaojin:group:list')")
    @GetMapping("/list/{catalogId}")
    public TableDataInfo list(@RequestParam Map<String, Object> params, @PathVariable String catalogId)
    {
        logger.info("这个数据是----------{}",params);
        Page<AttrGroup> page = attrGroupService.selectAttrGroupListForList(params, catalogId);
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setCode(HttpStatus.SUCCESS);
        tableDataInfo.setMsg("查询成功");
        tableDataInfo.setTotal(page.getTotal());
        tableDataInfo.setRows(page.getRecords());
        return tableDataInfo;
    }


    //product/machaojin/group/1/attr/relation

    @GetMapping("{attrgroupId}/attr/relation")
    public AjaxResult list(@PathVariable Long attrgroupId){
       List<Attr> attrList = attrService.selectAttrListAll(attrgroupId);
       return AjaxResult.success(attrList);
    }


    /**
     * 导出属性分组列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:group:export')")
    @Log(title = "属性分组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AttrGroup attrGroup)
    {
        List<AttrGroup> list = attrGroupService.selectAttrGroupList(attrGroup);
        ExcelUtil<AttrGroup> util = new ExcelUtil<AttrGroup>(AttrGroup.class);
        util.exportExcel(response, list, "属性分组数据");
    }

    /**
     * 获取属性分组详细信息
     */
//    @PreAuthorize("@ss.hasPermi('machaojin:group:query')")
    @GetMapping(value = "/info/{attrGroupId}")
    public AjaxResult getInfo(@PathVariable("attrGroupId") Long attrGroupId)
    {
        return AjaxResult.success(attrGroupService.selectAttrGroupByAttrGroupId(attrGroupId));
    }

    /**
     * 新增属性分组
     */
//    @PreAuthorize("@ss.hasPermi('machaojin:group:add')")
    @Log(title = "属性分组", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult add(@RequestBody AttrGroup attrGroup)
    {
        return toAjax(attrGroupService.insertAttrGroup(attrGroup));
    }

    /**
     * 修改属性分组
     */
//    @PreAuthorize("@ss.hasPermi('machaojin:group:edit')")
    @Log(title = "属性分组", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody AttrGroup attrGroup)
    {
        return toAjax(attrGroupService.updateAttrGroup(attrGroup));
    }

    /**
     * 删除属性分组
     */
    @PreAuthorize("@ss.hasPermi('machaojin:group:remove')")
    @Log(title = "属性分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attrGroupIds}")
    public AjaxResult remove(@PathVariable Long[] attrGroupIds)
    {
        return toAjax(attrGroupService.deleteAttrGroupByAttrGroupIds(attrGroupIds));
    }

    /**
     * 删除属性分组
     * {attrId: 2, attrGroupId: 1}
     * /group/attr/relation/delete
     */
    @PreAuthorize("@ss.hasPermi('machaojin:group:remove')")
    @Log(title = "属性分组", businessType = BusinessType.DELETE)
    @PostMapping("/attr/relation/delete")
    public AjaxResult remove(@RequestBody AttrRelationVo[] attrGroupIds)
    {
        return toAjax(attrGroupService.deleteFor(attrGroupIds));
    }

    //http://localhost:8087/api/product/machaojin/group/1/noattr/relation
    //获取没有被该分组关联的属性GET

    @GetMapping("/{attrGroupId}/noattr/relation")
    @PreAuthorize("@ss.hasPermi('machaojin:group:remove')")
    @Log(title = "属性分组", businessType = BusinessType.EXPORT)
    public AjaxResult getNoAttr(@PathVariable String attrGroupId,@RequestBody Map<String,String> params)
    {
        return AjaxResult.success(attrGroupService.listFor(attrGroupId,params));
    }


}
