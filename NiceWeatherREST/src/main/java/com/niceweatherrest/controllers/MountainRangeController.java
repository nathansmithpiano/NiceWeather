package com.niceweatherrest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niceweatherjpa.entities.MountainRange;
import com.niceweatherrest.services.MountainRangeService;

@RestController
@RequestMapping("mountainrange")
public class MountainRangeController {

	@Autowired
	private MountainRangeService mrSvc;

	@GetMapping("")
	public List<MountainRange> index() {
		return mrSvc.index();
	}

	@GetMapping("/{id}")
	public MountainRange findById(@PathVariable Integer id, HttpServletResponse res) {
		MountainRange mountainRange = mrSvc.findById(id);
		if (mountainRange != null) {
			res.setStatus(200);
			return mountainRange;
		} else {
			res.setStatus(404);
			return null;
		}
	}

}
