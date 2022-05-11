package com.niceweatherrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.niceweatherjpa.entities.Forecast;
import com.niceweatherjpa.entities.Period;

@SpringBootTest
class PeriodServiceTest {

	@Autowired
	private PeriodServiceImpl periodSvc;
	
	@Autowired
	private ForecastServiceImpl forcSvc;
	
	// Settings
	private final int periodId = 1;
	private final int forecastId = 1;

	@BeforeEach
	void setUp() throws Exception {
		assertNotNull(periodSvc);
		assertNotNull(forcSvc);
	}

	@AfterEach
	void tearDown() throws Exception {
		periodSvc = null;
		forcSvc = null;
	}

	@Test
	@DisplayName("PeriodService index()")
	void test_index() {
		assertNotNull(periodSvc.index());
		assertTrue(periodSvc.index().size() > 0);
	}
	
	@Test
	@DisplayName("PeriodService update()")
	void test_update() {
		// Update and restore name
		
		// Settings
		final String updatedName = "Updated Name";
		final int periodCount = periodSvc.index().size();
		
		// Find in DB
		Period period = periodSvc.findById(periodId);
		assertNotNull(period);
		final String initialName = period.getName();
		
		// Change locally
		period.setName(updatedName);
		
		// Update on DB
		Period updatedPeriod = periodSvc.update(period);
		assertNotNull(updatedPeriod);
		period = null;
		Period foundPeriod = periodSvc.findById(periodId);
		assertNotNull(foundPeriod);
		updatedPeriod = null;
		
		// Verify
		assertEquals(periodId, foundPeriod.getId());
		assertEquals(updatedName, foundPeriod.getName());
		
		// Revert locally
		foundPeriod.setName(initialName);
		
		// Revert on DB
		Period revertedPeriod = periodSvc.update(foundPeriod);
		assertNotNull(revertedPeriod);
		foundPeriod = null;
		
		// Verify
		assertEquals(periodId, revertedPeriod.getId());
		assertEquals(initialName, revertedPeriod.getName());
		
		// Verify nothing new created
		assertEquals(periodCount, periodSvc.index().size());
	}
	
	@Test
	@DisplayName("PeriodService create, read, delete with forecast")
	void test_CR_D() {
		// Create and delete new Period
		
		// Settings
		final int periodCount = periodSvc.index().size();
		final int forecastCount = forcSvc.index().size();
		final String periodName = "New Name";
		
		// Find forecast in DB
		Forecast forecast = forcSvc.findById(forecastId);
		assertNotNull(forecast);
		final int forecastPeriodCount = forecast.getPeriods().size();
		
		// Create new Period locally
		Period newPeriod = new Period();
		newPeriod.setName(periodName);
		
		// Set Forecast to Period
		newPeriod.setForecast(forecast);
		
		// Persist to DB
		Period createdPeriod = periodSvc.create(newPeriod);
		assertNotNull(createdPeriod);
		final int createdId = createdPeriod.getId();
		createdPeriod = null;
		Period foundPeriod = periodSvc.findById(createdId);
		assertNotNull(foundPeriod);
		
		// Find updated Forecast on DB
		Forecast updatedForecast = forcSvc.findById(forecastId);
		assertNotNull(updatedForecast);
		forecast = null;
		
		// Verify
		assertEquals(periodName, foundPeriod.getName());
		assertEquals(updatedForecast, foundPeriod.getForecast());
		assertTrue(updatedForecast.getPeriods().contains(foundPeriod));
		
		// Delete on DB
		assertTrue(periodSvc.deleteById(createdId));
		assertNull(periodSvc.findById(createdId));
		
		// Verify Forecast unchanged
		updatedForecast = null;
		Forecast foundForecast = forcSvc.findById(forecastId);
		assertNotNull(foundForecast);
		assertFalse(foundForecast.getPeriods().contains(foundPeriod));
		
		// Verify nothing remains
		assertEquals(forecastPeriodCount, foundForecast.getPeriods().size());
		assertEquals(periodCount, periodSvc.index().size());
		assertEquals(forecastCount, forcSvc.index().size());
	}
}
