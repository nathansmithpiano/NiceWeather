package com.niceweatherrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherrest.services.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService catSvc;

}
