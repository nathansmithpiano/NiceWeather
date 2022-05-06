package com.niceweatherrest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherjpa.entities.Geometry;
import com.niceweatherrest.repositories.GeometryRepository;

@Service
public class GeometryServiceImpl implements GeometryService {
	
	@Autowired
	private GeometryRepository geoRepo;

	@Override
	public List<Geometry> index() {
		return geoRepo.findAll();
	}

}
