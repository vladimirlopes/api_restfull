package com.vlsystem.project.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vlsystem.project.model.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, UUID>{

}
