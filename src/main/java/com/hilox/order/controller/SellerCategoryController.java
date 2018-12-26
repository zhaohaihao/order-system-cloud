package com.hilox.order.controller;

import com.hilox.order.exception.SellException;
import com.hilox.order.form.CategoryForm;
import com.hilox.order.model.ProductCategory;
import com.hilox.order.service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家类目Controller
 * Created by Hilox on 2018/12/26 0026.
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 类目列表
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList", productCategoryList);
        return new ModelAndView("category/list", map);
    }

    /**
     * 类目详情
     * @param categoryId
     * @param map
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null && categoryId > 0) {
            ProductCategory productCategory = productCategoryService.findOne(categoryId);
            map.put("productCategory", productCategory);
        }

        // 查询所有的类目
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList", productCategoryList);
        return new ModelAndView("category/index", map);
    }

    /**
     * 类目新增、更新
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "hilox-order/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        ProductCategory productCategory = new ProductCategory();
        try {
            Integer categoryId = categoryForm.getId();
            if (categoryId != null) {
                productCategory = productCategoryService.findOne(categoryId);
            }
            BeanUtils.copyProperties(categoryForm, productCategory);
            productCategoryService.save(productCategory);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "hilox-order/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "hilox-order/seller/category/index");
        return new ModelAndView("common/success", map);
    }
}
