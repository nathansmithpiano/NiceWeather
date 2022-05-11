package com.niceweatherrest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.Category;
import com.niceweatherrest.services.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryService catSvc;

	@GetMapping("")
	public List<Category> index() {
		return catSvc.index();
	}

	@GetMapping("/{id}")
	public Category findById(@PathVariable Integer id, HttpServletResponse res) {
		Category category = catSvc.findById(id);
		if (category != null) {
			res.setStatus(200);
			return category;
		} else {
			res.setStatus(404);
			return null;
		}
	}

	@PostMapping("category/create")
	public Category create(@RequestBody Category category, HttpServletRequest req, HttpServletResponse res) {
		try {
			catSvc.create(category);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(category.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			category = null;
		}

		return category;

	}

}
