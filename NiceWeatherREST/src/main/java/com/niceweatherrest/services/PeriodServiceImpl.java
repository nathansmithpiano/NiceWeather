package com.niceweatherrest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherrest.repositories.PeriodRepository;

@Service
public class PeriodServiceImpl implements PeriodService {
	
	@Autowired
	private PeriodRepository periodRepo;

}
