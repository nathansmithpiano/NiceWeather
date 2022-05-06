package com.niceweatherrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherrest.services.LocationService;

@RestController
public class LocationController {
	
	@Autowired
	private LocationService locSvc;

}
