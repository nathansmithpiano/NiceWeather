package com.niceweatherrest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.Point;
import com.niceweatherrest.services.PointService;

@RestController
@RequestMapping("point")
public class PointController {

	@Autowired
	private PointService pointSvc;

	@GetMapping("")
	public List<Point> index() {
		return pointSvc.index();
	}

	@GetMapping("/{id}")
	public Point findById(@PathVariable Integer id, HttpServletResponse res) {
		Point point = pointSvc.findById(id);
		if (point != null) {
			res.setStatus(200);
			return point;
		} else {
			res.setStatus(404);
			return null;
		}
	}

}
