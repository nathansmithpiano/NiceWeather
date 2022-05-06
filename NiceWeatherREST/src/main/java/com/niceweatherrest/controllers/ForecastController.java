package com.niceweatherrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherrest.services.ForecastService;

@RestController
public class ForecastController {
	
	@Autowired
	private ForecastService forcSvc;

}
