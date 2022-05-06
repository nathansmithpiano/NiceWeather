package com.niceweatherrest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherrest.repositories.MountainRangeRepository;

@Service
public class MountainRangeServiceImpl implements MountainRangeService {
	
	@Autowired
	private MountainRangeRepository mrRepo;

}
