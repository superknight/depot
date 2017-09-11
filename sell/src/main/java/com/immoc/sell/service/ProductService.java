package com.immoc.sell.service;

import com.immoc.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     *  查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存

    //减库存
}
