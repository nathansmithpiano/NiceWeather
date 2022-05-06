package com.niceweatherrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.Category;
import com.niceweatherrest.services.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService catSvc;
	
	@GetMapping("categories")
	public List<Category> index() {
		return catSvc.index();
	}

}
