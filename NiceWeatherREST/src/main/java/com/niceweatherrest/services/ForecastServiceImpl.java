//package com.niceweatherrest.services;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.niceweatherjpa.entities.Forecast;
//import com.niceweatherrest.repositories.ForecastRepository;
//
//@Service
//public class ForecastServiceImpl implements ForecastService {
//
//	@Autowired
//	private ForecastRepository forcRepo;
//
//	@Override
//	public List<Forecast> index() {
//		return forcRepo.findAll();
//	}
//
//	@Override
//	public Forecast findById(int id) {
//		Optional<Forecast> op = forcRepo.findById(id);
//		if (op.isPresent()) {
//			return op.get();
//		} else {
//			return null;
//		}
//	}
//
//	@Override
//	public Forecast create(Forecast forecast) {
//		return forcRepo.saveAndFlush(forecast);
//	}
//
//	@Override
//	public Forecast update(Forecast forecast) {
//		return forcRepo.save(forecast);
//	}
//
//	@Override
//	public boolean deleteById(int id) {
//		forcRepo.deleteById(id);
//		Optional<Forecast> op = forcRepo.findById(id);
//		if (op.isPresent()) {
//			return false;
//		} else {
//			return true;
//		}
//	}
//
//}
