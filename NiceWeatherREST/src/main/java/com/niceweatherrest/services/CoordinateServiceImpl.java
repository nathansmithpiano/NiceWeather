package com.niceweatherrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherjpa.entities.Coordinate;
import com.niceweatherrest.repositories.CoordinateRepository;

@Service
public class CoordinateServiceImpl implements CoordinateService {

	@Autowired
	private CoordinateRepository coordRepo;

	@Override
	public List<Coordinate> index() {
		return coordRepo.findAll();
	}

	@Override
	public Coordinate findById(int id) {
		Optional<Coordinate> op = coordRepo.findById(id);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	@Override
	public Coordinate create(Coordinate coordinate) {
		return coordRepo.saveAndFlush(coordinate);
	}

	@Override
	public Coordinate update(Coordinate coordinate) {
		return coordRepo.save(coordinate);
	}

	@Override
	public boolean deleteById(int id) {
		coordRepo.deleteById(id);
		Optional<Coordinate> op = coordRepo.findById(id);
		if (op.isPresent()) {
			return false;
		} else {
			return true;
		}
	}

}