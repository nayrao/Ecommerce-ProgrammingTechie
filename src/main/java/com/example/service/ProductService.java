package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ProductDto;
import com.example.exception.ProductNotExistException;
import com.example.model.Category;
import com.example.model.Product;
import com.example.repository.ProductRepo;

@Service
public class ProductService {
	@Autowired
	ProductRepo productRepo;

	public void createProduct(ProductDto productDto, Category category) {
		Product product=new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setImageUrl(productDto.getImageUrl());
		product.setCategory(category);
		
		productRepo.save(product);
		
	}
	
	public ProductDto getProductDto(Product product) {
		ProductDto productDto=new ProductDto();
		productDto.setCategoryId(product.getCategory().getId());
		productDto.setDescription(product.getDescription());
		productDto.setImageUrl(product.getImageUrl());
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		productDto.setId(product.getId());
		return productDto;
	}

	public List<ProductDto> getAllProducts() {
		List<Product> allProducts = productRepo.findAll();
		
		List<ProductDto> productDtos=new ArrayList<>();
		for(Product product: allProducts) {
			productDtos.add(getProductDto(product));
		}
		
		return productDtos;
	}

	public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
		Optional<Product> findByIdProduct = productRepo.findById(productId);
		
		if(!findByIdProduct.isPresent()) {
			throw new Exception("Product not present");
		}
		Product product = findByIdProduct.get();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setImageUrl(productDto.getImageUrl());
		productRepo.save(product);
	}

	public Product findById(Integer productId) {
		
		Optional<Product> optionalProduct = productRepo.findById(productId);
		if(optionalProduct.isEmpty()) {
			throw new ProductNotExistException("Product is invalid::"+productId);
		}
		
		return optionalProduct.get();
	}

}
