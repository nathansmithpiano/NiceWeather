package com.niceweatherrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niceweatherjpa.entities.Period;
import com.niceweatherrest.repositories.PeriodRepository;

@Service
public class PeriodServiceImpl implements PeriodService {

	@Autowired
	private PeriodRepository periodRepo;

	@Override
	public List<Period> index() {
		return periodRepo.findAll();
	}

	@Override
	public Period findById(int id) {
		Optional<Period> op = periodRepo.findById(id);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	@Override
	public Period create(Period period) {
		return periodRepo.saveAndFlush(period);
	}

	@Override
	public Period update(Period period) {
		return periodRepo.save(period);
	}

	@Override
	public boolean deleteById(int id) {
		periodRepo.deleteById(id);
		Optional<Period> op = periodRepo.findById(id);
		if (op.isPresent()) {
			return false;
		} else {
			return true;
		}
	}

}
