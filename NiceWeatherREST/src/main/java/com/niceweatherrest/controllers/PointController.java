package com.niceweatherrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherrest.services.PointService;

@RestController
public class PointController {
	
	@Autowired
	private PointService pointSvc;

}
