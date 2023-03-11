package com.machaojin.web;

import com.machaojin.domain.Category;
import com.machaojin.service.ICategoryService;
import com.machaojin.vo.Category2Vo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
@Slf4j
@Controller
public class ProductIndex {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = {"/","/index.html","/index"})
    public ModelAndView toIndex(ModelAndView modelAndView){
        //查询出商品的一级分类发送给前端
       List<Category> categories = categoryService.getCategoryLevel1();
        System.out.println(1);
        modelAndView.setViewName("index");
        modelAndView.addObject("category",categories);
        return modelAndView;
    }

    @GetMapping("/catalog/json")
    @ResponseBody
    public HashMap<String,List<Category2Vo>> getCategory(){
       return categoryService.getCategoryLevel23();
    }
}
