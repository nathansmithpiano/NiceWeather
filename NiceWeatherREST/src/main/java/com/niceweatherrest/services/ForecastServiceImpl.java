package com.niceweatherrest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherjpa.entities.Forecast;
import com.niceweatherrest.repositories.ForecastRepository;

@Service
public class ForecastServiceImpl implements ForecastService {
	
	@Autowired
	private ForecastRepository forcRepo;

	@Override
	public List<Forecast> index() {
		return forcRepo.findAll();
	}

}
