package com.niceweatherrest.services;

import java.util.List;

import com.niceweatherjpa.entities.MountainRange;

public interface MountainRangeService {

	List<MountainRange> index();

	MountainRange findById(int id);

	MountainRange create(MountainRange mountainRange);

	MountainRange update(MountainRange mountainRange);

	boolean deleteById(int id);

}
