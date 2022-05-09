package com.niceweatherrest.services;

import java.util.List;

import com.niceweatherjpa.entities.Geometry;

public interface GeometryService {

	List<Geometry> index();

	Geometry findById(int id);

	Geometry create(Geometry geometry);

	Geometry update(Geometry geometry);

	boolean deleteById(int id);

}
