package com.hilox.order.controller;

import com.hilox.order.enums.ResultEnum;
import com.hilox.order.exception.SellException;
import com.hilox.order.form.ProductForm;
import com.hilox.order.model.Product;
import com.hilox.order.model.ProductCategory;
import com.hilox.order.service.ProductCategoryService;
import com.hilox.order.service.ProductService;
import com.hilox.order.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
 * 卖家商品Cotroller
 * Created by Hilox on 2018/12/26 0026.
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 订单列表
     * @param page 第几页, 从第一页开始
     * @param size 一页有多少条数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<Product> productPage = productService.findAll(pageRequest);
        map.put("productPage", productPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    /**
     * 商品上架
     * @param productId 商品id
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "hilox-order/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_ON_SALE_SUCCESS.getMessage());
        map.put("url", "hilox-order/seller/order/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 商品下架
     * @param productId 商品id
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "hilox-order/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_OFF_SALE_SUCCESS.getMessage());
        map.put("url", "hilox-order/seller/order/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 商品详情
     * @param productId
     * @param map
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                      Map<String, Object> map) {
        if (StringUtils.isNotEmpty(productId)) {
            Product product = productService.findOne(productId);
            map.put("product", product);
        }

        // 查询所有的类目
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList", productCategoryList);
        return new ModelAndView("product/index", map);
    }

    /**
     * 商品保存、更新
     * @param productForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    //@CachePut(cacheNames = "product", key = "123")
    //@CacheEvict(cacheNames = "product", key = "123")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "hilox-order/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        Product product = new Product();
        try {
            String productId = productForm.getId();
            if (StringUtils.isNotEmpty(productId)) {
                product = productService.findOne(productId);
            } else {
                productForm.setId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(productForm, product);
            productService.save(product);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "hilox-order/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "hilox-order/seller/product/index");
        return new ModelAndView("common/success", map);
    }
}
