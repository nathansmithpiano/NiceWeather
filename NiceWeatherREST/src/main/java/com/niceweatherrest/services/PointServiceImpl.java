package com.niceweatherrest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherjpa.entities.Point;
import com.niceweatherrest.repositories.PointRepository;

@Service
public class PointServiceImpl implements PointService {
	
	@Autowired
	private PointRepository pointRepo;

	@Override
	public List<Point> index() {
		return pointRepo.findAll();
	}

}
