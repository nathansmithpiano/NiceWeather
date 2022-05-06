package com.niceweatherrest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherrest.repositories.ForecastRepository;

@Service
public class ForecastServiceImpl implements ForecastService {
	
	@Autowired
	private ForecastRepository forcRepo;

}
