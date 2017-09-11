package com.immoc.sell.controller;


import com.immoc.sell.VO.ProductInfoVO;
import com.immoc.sell.VO.ProductVO;
import com.immoc.sell.VO.ResultVO;
import com.immoc.sell.dataobject.ProductCategory;
import com.immoc.sell.dataobject.ProductInfo;
import com.immoc.sell.service.CategoryService;
import com.immoc.sell.service.ProductService;
import com.immoc.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //1.查询所有上架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.查询类目（一次性查询）
        //传统方法
        /*List<Integer> categoryTypeList = new ArrayList<>();
        for(ProductInfo productInfo:productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }*/
        //精简方法
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType()).collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
