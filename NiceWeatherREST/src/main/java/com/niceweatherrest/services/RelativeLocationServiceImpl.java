package com.niceweatherrest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherrest.repositories.RelativeLocationRepository;

@Service
public class RelativeLocationServiceImpl implements RelativeLocationService {
	
	@Autowired
	private RelativeLocationRepository rlRepo;

}
