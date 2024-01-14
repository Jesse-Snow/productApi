package com.api.newapplication.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.newapplication.model.ProductModel;


@Repository
public interface ProductRepository extends JpaRepository<ProductModel,UUID>{
}
