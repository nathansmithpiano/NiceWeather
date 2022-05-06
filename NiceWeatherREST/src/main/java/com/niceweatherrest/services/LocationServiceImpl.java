package com.niceweatherrest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherjpa.entities.Location;
import com.niceweatherrest.repositories.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationRepository locRepo;

	@Override
	public List<Location> index() {
		return locRepo.findAll();
	}

}
