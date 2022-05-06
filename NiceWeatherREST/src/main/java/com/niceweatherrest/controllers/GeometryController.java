package com.niceweatherrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.Geometry;
import com.niceweatherrest.services.GeometryService;

@RestController
public class GeometryController {
	
	@Autowired
	private GeometryService geoSvc;
	
	@GetMapping("geometry")
	public List<Geometry> index() {
		return geoSvc.index();
	}

}
