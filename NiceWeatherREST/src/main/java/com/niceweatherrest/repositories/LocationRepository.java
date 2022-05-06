package com.niceweatherrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niceweatherjpa.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
