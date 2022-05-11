package com.niceweatherrest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.Location;
import com.niceweatherrest.services.LocationService;

@RestController
@RequestMapping("location")
public class LocationController {

	@Autowired
	private LocationService locSvc;

	@GetMapping("")
	public List<Location> index() {
		return locSvc.index();
	}

	@GetMapping("/{id}")
	public Location findById(@PathVariable Integer id, HttpServletResponse res) {
		Location location = locSvc.findById(id);
		if (location != null) {
			res.setStatus(200);
			return location;
		} else {
			res.setStatus(404);
			return null;
		}
	}

}
