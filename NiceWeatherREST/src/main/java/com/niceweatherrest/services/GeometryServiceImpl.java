package com.niceweatherrest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherrest.repositories.GeometryRepository;

@Service
public class GeometryServiceImpl implements GeometryService {
	
	@Autowired
	private GeometryRepository geoRepo;

}
