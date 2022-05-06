package com.niceweatherrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherrest.services.RelativeLocationService;

@RestController
public class RelativeLocationController {
	
	@Autowired
	private RelativeLocationService rlSvc;

}
