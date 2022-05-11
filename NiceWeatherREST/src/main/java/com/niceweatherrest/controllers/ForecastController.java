package com.niceweatherrest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.Forecast;
import com.niceweatherrest.services.ForecastService;

@RestController
@RequestMapping("forecast")
public class ForecastController {
	
	@Autowired
	private ForecastService forcSvc;
	
	@GetMapping("")
	public List<Forecast> index() {
		return forcSvc.index();
	}
	
	@GetMapping("/{id}")
	public Forecast findById(@PathVariable Integer id, HttpServletResponse res) {
		Forecast forecast = forcSvc.findById(id);
		if (forecast != null) {
			res.setStatus(200);
			return forecast;
		} else {
			res.setStatus(404);
			return null;
		}
	}

}
