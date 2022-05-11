package com.niceweatherrest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.Geometry;
import com.niceweatherrest.services.GeometryService;

@RestController
@RequestMapping("geometry")
public class GeometryController {
	
	@Autowired
	private GeometryService geoSvc;
	
	@GetMapping("")
	public List<Geometry> index() {
		return geoSvc.index();
	}
	
	@GetMapping("/{id}")
	public Geometry findById(@PathVariable Integer id, HttpServletResponse res) {
		Geometry geometry = geoSvc.findById(id);
		if (geometry != null) {
			res.setStatus(200);
			return geometry;
		} else {
			res.setStatus(404);
			return null;
		}
	}

}
