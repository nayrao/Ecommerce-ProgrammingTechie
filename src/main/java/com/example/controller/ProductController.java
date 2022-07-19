package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.ApiResponse;
import com.example.dto.ProductDto;
import com.example.model.Category;
import com.example.repository.CategoryRepo;
import com.example.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryRepo categoryRepo;

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {

		Optional<Category> findByCategoryID = categoryRepo.findById(productDto.getCategoryId());
		if (!findByCategoryID.isPresent()) {
			return new ResponseEntity<>(new ApiResponse(false, "Category Id does not exist!"), HttpStatus.BAD_REQUEST);
		}
		productService.createProduct(productDto, findByCategoryID.get());
		
		return new ResponseEntity<>(new ApiResponse(true, "Product has been created"), HttpStatus.CREATED);
	}
	
	@GetMapping("/allProducts")
	public ResponseEntity<List<ProductDto>> getProducts(){
		
		 List<ProductDto> products = productService.getAllProducts();
		 return new ResponseEntity<>(products,HttpStatus.OK);
		
	}
	
	@PutMapping("/edit/{productId}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId,@RequestBody ProductDto productDto) throws Exception {

		Optional<Category> findByCategoryID = categoryRepo.findById(productDto.getCategoryId());
		if (!findByCategoryID.isPresent()) {
			return new ResponseEntity<>(new ApiResponse(false, "Category Id does not exist!"), HttpStatus.BAD_REQUEST);
		}
		productService.updateProduct(productDto, productId);
		
		return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.CREATED);
	}
}
