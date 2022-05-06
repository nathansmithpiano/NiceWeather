package com.niceweatherrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niceweatherjpa.entities.Geometry;

public interface GeometryRepository extends JpaRepository<Geometry, Integer> {

}
