package com.niceweatherrest.services;

import java.util.List;

import com.niceweatherjpa.entities.Point;

public interface PointService {
	
	long count();

	List<Point> index();

	Point findById(int id);

	Point create(Point point);

	Point update(Point point);

	boolean deleteById(int id);

}
