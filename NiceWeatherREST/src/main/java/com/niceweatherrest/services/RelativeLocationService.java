package com.niceweatherrest.services;

import java.util.List;

import com.niceweatherjpa.entities.RelativeLocation;

public interface RelativeLocationService {
	
	List<RelativeLocation> index();
	
	RelativeLocation findById(int id);

	RelativeLocation create(RelativeLocation relativeLocation);

	RelativeLocation update(RelativeLocation relativeLocation);

	boolean deleteById(int id);

}
