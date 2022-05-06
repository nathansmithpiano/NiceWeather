package com.niceweatherrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherrest.services.GeometryService;

@RestController
public class GeometryController {
	
	@Autowired
	private GeometryService geoSvc;

}
