package com.ms.user.controllers;

import com.ms.user.services.ShowProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductStockController {

    @Autowired
    ShowProductService productService;

    @GetMapping("/show")
    public ResponseEntity showProducts(){



        return productService.pedido();
    }
}
