package com.niceweatherrest.services;

import java.util.List;

import com.niceweatherjpa.entities.Location;

public interface LocationService {
	
	long count();

	List<Location> index();

	Location findById(int id);

	Location create(Location location);

	Location update(Location location);

	boolean deleteById(int id);

}
