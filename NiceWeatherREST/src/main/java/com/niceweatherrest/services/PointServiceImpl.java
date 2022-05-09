//package com.niceweatherrest.services;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.niceweatherjpa.entities.Point;
//import com.niceweatherrest.repositories.PointRepository;
//
//@Service
//public class PointServiceImpl implements PointService {
//
//	@Autowired
//	private PointRepository pointRepo;
//
//	@Override
//	public List<Point> index() {
//		return pointRepo.findAll();
//	}
//
//	@Override
//	public Point findById(int id) {
//		Optional<Point> op = pointRepo.findById(id);
//		if (op.isPresent()) {
//			return op.get();
//		} else {
//			return null;
//		}
//	}
//
//	@Override
//	public Point create(Point point) {
//		return pointRepo.saveAndFlush(point);
//	}
//
//	@Override
//	public Point update(Point point) {
//		return pointRepo.save(point);
//	}
//
//	@Override
//	public boolean deleteById(int id) {
//		pointRepo.deleteById(id);
//		Optional<Point> op = pointRepo.findById(id);
//		if (op.isPresent()) {
//			return false;
//		} else {
//			return true;
//		}
//	}
//
//}
