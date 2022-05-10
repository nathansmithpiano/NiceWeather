package com.niceweatherjpa.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PeriodTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Period period1;
	private Period period2;
	
	// Settings
	final int period1Id = 1;
	final int period1Number = 1;
	final int period1ForecastId = 1;
	final String period1ForecastURL = "https://api.weather.gov/gridpoints/PUB/33,107/forecast";
	final int period2Id = 15;
	final int period2Number = 1;
	final int period2ForecastId = 2;
	final String period2ForecastURL = "https://api.weather.gov/gridpoints/PUB/33,107/forecast/hourly";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("NiceWeatherJPA");
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}
	
	@BeforeEach
	void setUp() {
		em = emf.createEntityManager();
		period1 = em.find(Period.class, period1Id);
		assertNotNull(period1);
		period2 = em.find(Period.class, period2Id);
		assertNotNull(period2);
	}
	
	@AfterEach
	void tearDown() {
		em.close();
		period1 = null;
	}

	@Test
	@DisplayName("Period mapping")
	void test_Period_mapping() {
		assertEquals(period1Id, period1.getId());
		assertEquals(period1Number, period1.getNumber());
		assertEquals(period2Id, period2.getId());
		assertEquals(period2Number, period2.getNumber());
	}
	
	@Test
	@DisplayName("Period Forecast mapping")
	void test_Period_Forecast_mapping() {
		Forecast period1Forecast = period1.getForecast();
		assertNotNull(period1Forecast);
		assertEquals(period1ForecastId, period1Forecast.getId());
		assertEquals(period1ForecastURL, period1Forecast.getUrl());
		Forecast period2Forecast = period2.getForecast();
		assertNotNull(period2Forecast);
		assertEquals(period2ForecastId, period2Forecast.getId());
		assertEquals(period2ForecastURL, period2Forecast.getUrl());
	}

}
