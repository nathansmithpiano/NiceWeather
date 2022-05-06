package com.niceweatherrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niceweatherjpa.entities.Point;

public interface PointRepository extends JpaRepository<Point, Integer> {

}
