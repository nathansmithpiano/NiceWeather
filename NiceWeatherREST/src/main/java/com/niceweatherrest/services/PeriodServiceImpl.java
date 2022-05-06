package com.niceweatherrest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherjpa.entities.Period;
import com.niceweatherrest.repositories.PeriodRepository;

@Service
public class PeriodServiceImpl implements PeriodService {
	
	@Autowired
	private PeriodRepository periodRepo;

	@Override
	public List<Period> index() {
		return periodRepo.findAll();
	}

}
