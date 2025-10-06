package com.ms.warehouse.controllers;

import com.ms.warehouse.models.ProductEntity;
import com.ms.warehouse.repositories.ProductRepository;
import com.ms.warehouse.services.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductShowController {

    @Autowired
    ProductList productList;

    @GetMapping("/show")
    public ResponseEntity showProducts(){
        System.out.println("Recebendo pedidos... em get show");
        List<ProductEntity> products = productList.listProduct();

        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(products);
    }
}
