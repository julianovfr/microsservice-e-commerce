package com.ms.warehouse.controllers;

import com.ms.warehouse.AddProductDto;
import com.ms.warehouse.consumers.StockConsumer;
import com.ms.warehouse.models.ProductEntity;
import com.ms.warehouse.models.StockEntity;
import com.ms.warehouse.repositories.ProductRepository;
import com.ms.warehouse.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AddProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StockRepository stockRepository;


    @PostMapping("/add-product")
    public ResponseEntity saveProduct(@RequestBody  AddProductDto addProductDto){


        System.out.println("Chegando requisicao... produto: "+addProductDto.name());

        if((productRepository.findByName(addProductDto.name())).isEmpty()){
            ProductEntity productEntity = new ProductEntity();
            StockEntity stockEntity = new StockEntity();

            productEntity.setName(addProductDto.name());
            productEntity.setStatus(addProductDto.status());

            stockEntity.setQtd(addProductDto.qtd());
            stockEntity.setStatus(addProductDto.status());

            stockEntity.setProduct(productEntity);
            productEntity.setStock(stockEntity);

            productRepository.save(productEntity);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
