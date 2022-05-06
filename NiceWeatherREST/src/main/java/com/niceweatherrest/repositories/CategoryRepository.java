package com.niceweatherrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niceweatherjpa.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
}
