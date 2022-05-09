package com.niceweatherrest.services;

import java.util.List;

import com.niceweatherjpa.entities.Coordinate;

public interface CoordinateService {

	List<Coordinate> index();

	Coordinate findById(int id);

	Coordinate create(Coordinate coordinate);

	Coordinate update(Coordinate coordinate);

	boolean deleteById(int id);

}
