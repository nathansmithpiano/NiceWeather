package com.niceweatherrest.services;

import java.util.List;

import com.niceweatherjpa.entities.Category;

public interface CategoryService {
	
	List<Category> index();
	Category findById(int id);
	Category create(Category category);
	Category update(Category category);
	boolean deleteById(int id);

}
