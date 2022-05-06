package com.niceweatherrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.Forecast;
import com.niceweatherrest.services.ForecastService;

@RestController
public class ForecastController {
	
	@Autowired
	private ForecastService forcSvc;
	
	@GetMapping("forecasts")
	public List<Forecast> index() {
		return forcSvc.index();
	}

}
