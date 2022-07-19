package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.ApiResponse;
import com.example.model.Category;
import com.example.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
		categoryService.createCategory(category);
		return new ResponseEntity<>(new ApiResponse(true, "A new Category created"),HttpStatus.CREATED);
	}

	@GetMapping("/list")
	public List<Category> listOfCategories(){
		
		return categoryService.listOfCategory();
	}
	
	  @PutMapping("/category/{id}")
	    public ResponseEntity<ApiResponse> updateCategory(@RequestBody Category category,  @PathVariable("id") Integer Id)
	    {
		  if(!categoryService.findById(Id)) {
			  return new ResponseEntity<>(new ApiResponse(false, "Category is not found"),HttpStatus.NOT_FOUND);
		  }
	         categoryService.updateCategory(category, Id);
	         
	         return new ResponseEntity<>(new ApiResponse(true, "Category Has been updated"),HttpStatus.OK);
	    }
	  
	  @DeleteMapping("/category/{id}")
	    public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable("id") Integer Id)
	    {
		  if(!categoryService.findById(Id)) {
			  return new ResponseEntity<>(new ApiResponse(false, "Category Id does not exists!"),HttpStatus.NOT_FOUND);
		  }
		  
	        categoryService.deleteCategoryById(Id);
	        return new ResponseEntity<>(new ApiResponse(true, "Category deleted successfully!!"),HttpStatus.OK);
	    }
	
}
