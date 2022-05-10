package com.niceweatherrest.services;

import java.util.List;
import java.util.Optional;

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

	@Override
	public RelativeLocation findById(int id) {
		Optional<RelativeLocation> op = rlRepo.findById(id);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	@Override
	public RelativeLocation create(RelativeLocation relativeLocation) {
		return rlRepo.saveAndFlush(relativeLocation);
	}

	@Override
	public RelativeLocation update(RelativeLocation relativeLocation) {
		return rlRepo.save(relativeLocation);
	}

	@Override
	public boolean deleteById(int id) {
		rlRepo.deleteById(id);
		Optional<RelativeLocation> op = rlRepo.findById(id);
		if (op.isPresent()) {
			return false;
		} else {
			return true;
		}
	}

}
