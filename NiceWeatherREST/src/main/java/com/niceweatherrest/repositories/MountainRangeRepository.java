package com.niceweatherrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niceweatherjpa.entities.MountainRange;

public interface MountainRangeRepository extends JpaRepository<MountainRange, Integer> {

}
