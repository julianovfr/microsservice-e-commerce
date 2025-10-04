package com.ms.warehouse.services;

import com.ms.warehouse.models.ProductEntity;
import com.ms.warehouse.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductList {

    @Autowired
    ProductRepository productRepository;

    public List<ProductEntity> listProduct(){

        return productRepository.findAll();
    }
}
