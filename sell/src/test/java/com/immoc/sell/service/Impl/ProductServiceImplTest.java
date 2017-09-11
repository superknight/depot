package com.immoc.sell.service.Impl;

import com.immoc.sell.dataobject.ProductInfo;
import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productService.findOne("1234554554");
        System.out.println(productInfo.toString());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest request = PageRequest.of(0,2);
        Page<ProductInfo> productInfoPage =productService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0,productInfoPage.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("12345");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(35));
        productInfo.setProductStock(10);
        productInfo.setProductDescription("非常好喝的虾");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(3);

        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

}