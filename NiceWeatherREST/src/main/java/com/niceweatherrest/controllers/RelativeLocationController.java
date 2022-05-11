package com.niceweatherrest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.RelativeLocation;
import com.niceweatherrest.services.RelativeLocationService;

@RestController
@RequestMapping("relativelocation")
public class RelativeLocationController {
	
	@Autowired
	private RelativeLocationService rlSvc;
	
	@GetMapping("")
	public List<RelativeLocation> index() {
		return rlSvc.index();
	}
	
	@GetMapping("/{id}")
	public RelativeLocation findById(@PathVariable Integer id, HttpServletResponse res) {
		RelativeLocation relativeLocation = rlSvc.findById(id);
		if (relativeLocation != null) {
			res.setStatus(200);
			return relativeLocation;
		} else {
			res.setStatus(404);
			return null;
		}
	}

}
