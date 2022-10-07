package com.machaojin.controller;

import java.util.List;
import java.util.stream.Collectors;
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
import com.machaojin.domain.Category;
import com.machaojin.service.ICategoryService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 商品三级分类Controller
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/machaojin/category")
public class CategoryController extends BaseController
{
    @Autowired
    private ICategoryService categoryService;


    @GetMapping("/list/tree")
    public List<Category> getAll(){

        //找出全部的数据
        List<Category> categories = categoryService.selectCategoryList(null);
        //组成树形结构
        //找出所有的一级分类以及找到所有的子菜单
        List<Category> leval1Menus = categories
                .stream()
                //过滤出来所有的一级菜单
                .filter(category -> category.getParentCid() == 0)
                //找到所有一级菜单的的子菜单
                .peek((menu) -> menu.setChildren(getChildrens(menu,categories)))
                .sorted((menu1,menu2)-> {
                    //getSort可能是空的会爆异常
                    return (int) ((int) (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort()));
                })
                //变成一个集合
                .collect(Collectors.toList());
        //

        return leval1Menus;
    }


    /**
     * 找到所有一级菜单的子菜单
     * @param root 当前菜单
     * @param all 所有的菜单
     * @return 返回父菜单
     */
    public List<Category> getChildrens(Category root,List<Category> all){
        List<Category> collect = all.stream()
                .filter(category -> {
                    return category.getParentCid().equals(root.getCatId());
                })
                //递归找到子菜单
                .peek(category -> {
                    category.setChildren(getChildrens(category, all));
                })
                //排序
                .sorted((menu1,menu2)->{
                    //getSort可能是空的会爆异常
                    return (int) ((int) (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort()));
                })
                .collect(Collectors.toList());
        return collect;
    }

    /**
     * 查询商品三级分类列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(Category category)
    {
        startPage();
        List<Category> list = categoryService.selectCategoryList(category);
        return getDataTable(list);
    }

    /**
     * 导出商品三级分类列表
     */
    @PreAuthorize("@ss.hasPermi('machaojin:category:export')")
    @Log(title = "商品三级分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        util.exportExcel(response, list, "商品三级分类数据");
    }

    /**
     * 获取商品三级分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('machaojin:category:query')")
    @GetMapping(value = "/{catId}")
    public AjaxResult getInfo(@PathVariable("catId") Long catId)
    {
        return AjaxResult.success(categoryService.selectCategoryByCatId(catId));
    }

    /**
     * 新增商品三级分类
     */
    @PreAuthorize("@ss.hasPermi('machaojin:category:add')")
    @Log(title = "商品三级分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Category category)
    {
        return toAjax(categoryService.insertCategory(category));
    }

    /**
     * 修改商品三级分类
     */
    @PreAuthorize("@ss.hasPermi('machaojin:category:edit')")
    @Log(title = "商品三级分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Category category)
    {
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * 删除商品三级分类
     */
    @PreAuthorize("@ss.hasPermi('machaojin:category:remove')")
    @Log(title = "商品三级分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{catIds}")
    public AjaxResult remove(@PathVariable Long[] catIds)
    {
        return toAjax(categoryService.deleteCategoryByCatIds(catIds));
    }
}
