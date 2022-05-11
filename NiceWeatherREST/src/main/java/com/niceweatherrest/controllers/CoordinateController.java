package com.niceweatherrest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.Coordinate;
import com.niceweatherrest.services.CoordinateService;

@RestController
@RequestMapping("coordinate")
public class CoordinateController {
	
	@Autowired
	private CoordinateService coordSvc;
	
	@GetMapping("")
	public List<Coordinate> index() {
		return coordSvc.index();
	}
	
	@GetMapping("/{id}")
	public Coordinate findById(@PathVariable Integer id, HttpServletResponse res) {
		Coordinate coordinate = coordSvc.findById(id);
		if (coordinate != null) {
			res.setStatus(200);
			return coordinate;
		} else {
			res.setStatus(404);
			return null;
		}
	}

}
