package com.niceweatherrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherjpa.entities.Category;
import com.niceweatherjpa.entities.Location;
import com.niceweatherrest.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository catRepo;

	@Override
	public List<Category> index() {
		return catRepo.findAll();
	}
	
	@Override
	public Category findById(int id) {
		Optional<Category> op = catRepo.findById(id);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	@Override
	public Category create(Category category) {
		return catRepo.saveAndFlush(category);
	}

	@Override
	public Category update(Category category) {
		return catRepo.save(category);
	}

	@Override
	public boolean deleteById(int id) {
		catRepo.deleteById(id);
		Optional<Category> op = catRepo.findById(id);
		if (op.isPresent()) {
			return false;
		} else {
			return true;
		}
	}

	

}
