package com.niceweatherrest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherrest.repositories.PointRepository;

@Service
public class PointServiceImpl implements PointService {
	
	@Autowired
	private PointRepository pointRepo;

}
