package com.niceweatherrest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherjpa.entities.MountainRange;
import com.niceweatherrest.repositories.MountainRangeRepository;

@Service
public class MountainRangeServiceImpl implements MountainRangeService {
	
	@Autowired
	private MountainRangeRepository mrRepo;

	@Override
	public List<MountainRange> index() {
		return mrRepo.findAll();
	}

}
