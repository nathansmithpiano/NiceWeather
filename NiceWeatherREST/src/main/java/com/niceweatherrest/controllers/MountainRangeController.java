package com.niceweatherrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.MountainRange;
import com.niceweatherrest.services.MountainRangeService;

@RestController
public class MountainRangeController {
	
	@Autowired
	private MountainRangeService mrSvc;
	
	@GetMapping("mountainranges")
	public List<MountainRange> index() {
		return mrSvc.index();
	}

}
