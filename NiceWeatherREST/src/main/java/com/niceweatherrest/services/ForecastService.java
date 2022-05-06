package com.niceweatherrest.services;

import java.util.List;

import com.niceweatherjpa.entities.Forecast;

public interface ForecastService {
	
	List<Forecast> index();

}
