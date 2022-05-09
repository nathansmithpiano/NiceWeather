//package com.niceweatherrest.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.niceweatherjpa.entities.Period;
//import com.niceweatherrest.services.PeriodService;
//
//@RestController
//public class PeriodController {
//	
//	@Autowired
//	private PeriodService periodSvc;
//	
//	@GetMapping("periods")
//	public List<Period> index() {
//		return periodSvc.index();
//	}
//
//}
