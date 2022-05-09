package com.niceweatherrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherjpa.entities.Geometry;
import com.niceweatherrest.repositories.GeometryRepository;

@Service
public class GeometryServiceImpl implements GeometryService {

	@Autowired
	private GeometryRepository geoRepo;

	@Override
	public List<Geometry> index() {
		return geoRepo.findAll();
	}

	@Override
	public Geometry findById(int id) {
		Optional<Geometry> op = geoRepo.findById(id);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	@Override
	public Geometry create(Geometry geometry) {
		return geoRepo.saveAndFlush(geometry);
	}

	@Override
	public Geometry update(Geometry geometry) {
		return geoRepo.save(geometry);
	}

	@Override
	public boolean deleteById(int id) {
		geoRepo.deleteById(id);
		Optional<Geometry> op = geoRepo.findById(id);
		if (op.isPresent()) {
			return false;
		} else {
			return true;
		}
	}

}
