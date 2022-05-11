package com.niceweatherrest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.Period;
import com.niceweatherrest.services.PeriodService;

@RestController
@RequestMapping("period")
public class PeriodController {
	
	@Autowired
	private PeriodService periodSvc;
	
	@GetMapping("")
	public List<Period> index() {
		return periodSvc.index();
	}
	
	@GetMapping("/{id}")
	public Period findById(@PathVariable Integer id, HttpServletResponse res) {
		Period period = periodSvc.findById(id);
		if (period != null) {
			res.setStatus(200);
			return period;
		} else {
			res.setStatus(404);
			return null;
		}
	}

}
