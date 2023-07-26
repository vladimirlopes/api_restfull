package com.vlsystem.project.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vlsystem.project.dto.ProductRecordDto;
import com.vlsystem.project.model.ProductModel;
import com.vlsystem.project.repository.ProductRepository;

import jakarta.validation.Valid;

@RestController

public class ProductController {
@Autowired
private ProductRepository productsRepository;
@PostMapping("/products")
public ResponseEntity<ProductModel>saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
	var productModel = new ProductModel();
	//recebe Dto ev transforma em model
	BeanUtils.copyProperties(productRecordDto, productModel);
	return ResponseEntity.status(HttpStatus.CREATED).body(productsRepository.save(productModel));
	
}
}
