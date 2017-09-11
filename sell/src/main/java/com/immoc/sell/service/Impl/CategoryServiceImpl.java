package com.immoc.sell.service.Impl;

import com.immoc.sell.dataobject.ProductCategory;
import com.immoc.sell.repository.ProductCategoryRepository;
import com.immoc.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        Iterable<ProductCategory> getAll = repository.findAll();
        List<ProductCategory> list = new ArrayList<ProductCategory>();
        getAll.forEach(list::add);
        return list;
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
