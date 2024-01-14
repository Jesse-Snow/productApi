package com.api.newapplication.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.newapplication.dto.ProductDto;
import com.api.newapplication.model.ProductModel;
import com.api.newapplication.repository.ProductRepository;

import jakarta.validation.Valid;

@RestController
public class ProductController {
    
    @Autowired
    ProductRepository productRepository;

    @PostMapping("/product")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductDto productDto){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productRepository.save(productModel));
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductModel>> getProducts(){
        return ResponseEntity
                 .status(HttpStatus.OK)
                 .body(productRepository.findAll());
    }
}
