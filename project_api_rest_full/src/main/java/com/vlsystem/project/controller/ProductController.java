package com.vlsystem.project.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.vlsystem.project.dto.ProductRecordDto;
import com.vlsystem.project.model.ProductModel;
import com.vlsystem.project.repository.ProductRepository;

import jakarta.validation.Valid;

@RestController

public class ProductController {
	@Autowired
	private ProductRepository productRepository;

	@GetMapping("produto")
	public ResponseEntity<List<ProductModel>> getAllProducts() {
		return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
	}

	@GetMapping("/produto/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
		Optional<ProductModel> productO = productRepository.findById(id);
		if (productO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(productO.get());
	}

	@PostMapping("/produto")
	public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
		var productModel = new ProductModel();
		// recebe Dto ev transforma em model
		BeanUtils.copyProperties(productRecordDto, productModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));

	}

	@DeleteMapping("/produto/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
		Optional<ProductModel> productO = productRepository.findById(id);
		if (productO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
		}
		productRepository.delete(productO.get());
		return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com  sucesso.");
	}

	@PutMapping("/produto/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid ProductRecordDto productRecordDto) {
		Optional<ProductModel> productO = productRepository.findById(id);
		if (productO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
		}
		var productModel = productO.get();
		BeanUtils.copyProperties(productRecordDto, productModel);
		return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
	}
}
