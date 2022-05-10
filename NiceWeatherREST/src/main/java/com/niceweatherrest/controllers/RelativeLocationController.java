package com.niceweatherrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.RelativeLocation;
import com.niceweatherrest.services.RelativeLocationService;

@RestController
public class RelativeLocationController {
	
	@Autowired
	private RelativeLocationService rlSvc;
	
	@GetMapping("relativelocations")
	public List<RelativeLocation> index() {
		return rlSvc.index();
	}

}
