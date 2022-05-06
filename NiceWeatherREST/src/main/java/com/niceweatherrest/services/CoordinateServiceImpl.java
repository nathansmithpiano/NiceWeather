package com.niceweatherrest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherjpa.entities.Coordinate;
import com.niceweatherrest.repositories.CoordinateRepository;

@Service
public class CoordinateServiceImpl implements CoordinateService {
	
	@Autowired
	private CoordinateRepository coordRepo;

	@Override
	public List<Coordinate> index() {
		return coordRepo.findAll();
	}

}
