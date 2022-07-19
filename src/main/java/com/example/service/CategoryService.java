package com.example.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Category;
import com.example.repository.CategoryRepo;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepo categoryRepo;
	
	public void createCategory(Category category) {
		
		categoryRepo.save(category);
	}

	public List<Category> listOfCategory() {
		
		return categoryRepo.findAll();
	}

	public Category updateCategory(Category category, Integer id) {
		Category catDB = categoryRepo.findById(id).get();
		 if (Objects.nonNull(category.getCategoryName())
		            && !"".equalsIgnoreCase(
		                category.getCategoryName())) {
		            catDB.setCategoryName(category.getCategoryName());
		        }
		 if (Objects.nonNull(category.getDescription())
		            && !"".equalsIgnoreCase(
		                category.getDescription())) {
		            catDB.setDescription(category.getDescription());
		        }
		return categoryRepo.save(catDB);
	}

	public boolean findById(Integer id) {
		// TODO Auto-generated method stub
		return categoryRepo.findById(id).isPresent();
	}

	public void deleteCategoryById(Integer id) {
		categoryRepo.deleteById(id);
		
	}

}
