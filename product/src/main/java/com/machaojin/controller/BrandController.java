package com.machaojin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.machaojin.valid.AddGroups;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
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
import com.machaojin.domain.Brand;
import com.machaojin.service.IBrandService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 品牌Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/brand")
@Transactional(rollbackFor = Exception.class)
public class BrandController extends BaseController
{
    @Autowired
    private IBrandService brandService;

    /**
     * 查询品牌列表
     * http://localhost:8087/api/product/machaojin/brand/list?t=1665223037383&page=1&limit=10&key=
     */
//    @PreAuthorize("@ss.hasPermi('machaojin:brand:list')")
    @GetMapping("/list")
    public TableDataInfo list(Brand brand)
    {
        startPage();
        List<Brand> list = brandService.selectBrandList(brand);
        return getDataTable(list);
    }

    /**
     * 导出品牌列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:brand:export')")
    @Log(title = "品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Brand brand)
    {
        List<Brand> list = brandService.selectBrandList(brand);
        ExcelUtil<Brand> util = new ExcelUtil<Brand>(Brand.class);
        util.exportExcel(response, list, "品牌数据");
    }

    /**
     * 获取品牌详细信息
     */
//    @PreAuthorize("@ss.hasPermi('machaojin:brand:query')")
    @GetMapping(value = "/info/{brandId}")
    public AjaxResult getInfo(@PathVariable("brandId") Long brandId)
    {
        return AjaxResult.success(brandService.selectBrandByBrandId(brandId));
    }

    /**
     * 新增品牌
     * Valid: 告诉SpringMVC这个字段需要被校验
     * BindingResult:得到数据校验的结果
     * Validated(value = {AddGroups.class}):对数据校验进行分组
     */
//    @PreAuthorize("@ss.hasPermi('machaojin:brand:add')")
    @Log(title = "品牌", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated(value = {AddGroups.class}) @RequestBody Brand brand/*,BindingResult result*/)
    {
//        //定义Map用于存储错误
//        Map<String,String> map = new HashMap<>();
//        //如果发生错误
//        if (result.hasErrors()){
//            List<ObjectError> allErrors = result.getAllErrors();
//            allErrors.forEach((error) -> {
//                map.put(error.getObjectName(),error.getDefaultMessage());
//            });
//            return new AjaxResult(400,"数据校验错误",map);
//        }else{
//            return toAjax(brandService.insertBrand(brand));
//        }
        return toAjax(brandService.insertBrand(brand));
    }

    /**
     * 修改品牌
     *
     */
    @PreAuthorize("@ss.hasPermi('machaojin:brand:edit')")
    @Log(title = "品牌", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody Brand brand)
    {

        return toAjax(brandService.updateBrand(brand));
    }

    /**
     * 删除品牌
     */
    @PreAuthorize("@ss.hasPermi('machaojin:brand:remove')")
    @Log(title = "品牌", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] brandIds)
    {
        return toAjax(brandService.deleteBrandByBrandIds(brandIds));
    }
}
