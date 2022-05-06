package com.niceweatherrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.Location;
import com.niceweatherrest.services.LocationService;

@RestController
public class LocationController {
	
	@Autowired
	private LocationService locSvc;
	
	@GetMapping("locations")
	public List<Location> index() {
		return locSvc.index();
	}

}
