package com.niceweatherrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.Coordinate;
import com.niceweatherrest.services.CoordinateService;

@RestController
public class CoordinateController {
	
	@Autowired
	private CoordinateService coordSvc;
	
	@GetMapping("coordinates")
	public List<Coordinate> index() {
		return coordSvc.index();
	}

}
