package com.niceweatherrest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherjpa.entities.RelativeLocation;
import com.niceweatherrest.repositories.RelativeLocationRepository;

@Service
public class RelativeLocationServiceImpl implements RelativeLocationService {
	
	@Autowired
	private RelativeLocationRepository rlRepo;

	@Override
	public List<RelativeLocation> index() {
		return rlRepo.findAll();
	}

}
