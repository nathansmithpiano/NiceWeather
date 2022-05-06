package com.niceweatherrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niceweatherjpa.entities.Coordinate;

public interface CoordinateRepository extends JpaRepository<Coordinate, Integer> {

}
