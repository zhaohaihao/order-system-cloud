package com.hilox.order.controller;

import com.hilox.order.model.Product;
import com.hilox.order.model.ProductCategory;
import com.hilox.order.service.ProductCategoryService;
import com.hilox.order.service.ProductService;
import com.hilox.order.utils.ResultVOUtil;
import com.hilox.order.vo.ProductCategoryVO;
import com.hilox.order.vo.ProductVO;
import com.hilox.order.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品Controller
 * Created by Hilox on 2018/12/19 0019.
 */
@Controller
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    @ResponseBody
    @Cacheable(cacheNames = "product", key = "#sellerId", condition = "#sellerId.length() > 3", unless = "#result.getCode() != 0") // cacheNames可以理解为前缀
    public ResultVO list(@RequestParam("sellerId") String sellerId) {
        // 1. 查询所有上架的商品
        List<Product> productList = productService.findUpAll();

        // 2. 查询类目(一次性查询)
        List<Integer> categoryCodeList = productList.stream().map(e -> e.getCategoryCode()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCodeIn(categoryCodeList);

        // 3. 数据拼装
        List<ProductCategoryVO> productCategoryVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            BeanUtils.copyProperties(productCategory, productCategoryVO);

            List<ProductVO> productInfoVOList = new ArrayList<>();
            for (Product product : productList) {
                ProductVO productVO = new ProductVO();
                BeanUtils.copyProperties(product, productVO);
                productInfoVOList.add(productVO);
            }

            productCategoryVO.setProductInfoVOList(productInfoVOList);
            productCategoryVOList.add(productCategoryVO);
        }

        return ResultVOUtil.success(productCategoryVOList);
    }
}
