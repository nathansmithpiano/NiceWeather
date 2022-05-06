package com.niceweatherrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.Point;
import com.niceweatherrest.services.PointService;

@RestController
public class PointController {
	
	@Autowired
	private PointService pointSvc;
	
	@GetMapping("points")
	public List<Point> index() {
		return pointSvc.index();
	}

}
