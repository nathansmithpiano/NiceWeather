package com.niceweatherrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherrest.services.PeriodService;

@RestController
public class PeriodController {
	
	@Autowired
	private PeriodService periodSvc;

}
