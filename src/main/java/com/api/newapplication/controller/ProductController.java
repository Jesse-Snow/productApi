package com.api.newapplication.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable UUID id){
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity
                     .status(HttpStatus.NOT_FOUND)
                     .body("Product not found");
        }
        return ResponseEntity
                 .status(HttpStatus.OK)
                 .body(product.get());
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductDto productDto){
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity
                     .status(HttpStatus.NOT_FOUND)
                     .body("Product not found");
        }
        var productModel = product.get();
        BeanUtils.copyProperties(productDto, productModel);
        return ResponseEntity 
                 .status(HttpStatus.OK)
                 .body(productRepository.save(productModel));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable UUID id){
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity
                     .status(HttpStatus.NOT_FOUND)
                     .body("Product not found");
        }
        productRepository.delete(product.get());
        return ResponseEntity
                 .status(HttpStatus.OK)
                 .body("Product Deleted");
    }

}
