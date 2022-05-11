package com.niceweatherrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherjpa.entities.Location;
import com.niceweatherrest.repositories.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locRepo;
	
	@Override
	public long count() {
		return locRepo.count();
	}

	@Override
	public List<Location> index() {
		return locRepo.findAll();
	}

	@Override
	public Location findById(int id) {
		Optional<Location> op = locRepo.findById(id);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	@Override
	public Location create(Location location) {
		return locRepo.saveAndFlush(location);
	}

	@Override
	public Location update(Location location) {
		return locRepo.save(location);
	}

	@Override
	public boolean deleteById(int id) {
		locRepo.deleteById(id);
		Optional<Location> op = locRepo.findById(id);
		if (op.isPresent()) {
			return false;
		} else {
			return true;
		}
	}

}
