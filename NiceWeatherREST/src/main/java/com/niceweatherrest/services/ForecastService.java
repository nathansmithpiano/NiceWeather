package com.niceweatherrest.services;

import java.util.List;

import com.niceweatherjpa.entities.Forecast;

public interface ForecastService {

	List<Forecast> index();

	Forecast findById(int id);

	Forecast create(Forecast forecast);

	Forecast update(Forecast forecast);

	boolean deleteById(int id);

}
