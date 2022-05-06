package com.niceweatherjpa.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ForecastTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Forecast forecast;
	
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
		forecast = em.find(Forecast.class, 1);
	}
	
	@AfterEach
	void tearDown() {
		em.close();
		forecast = null;
	}

	@Test
	void test_Forecast_mapping() {
		assertNotNull(forecast);
		assertEquals(1, forecast.getId());
		assertEquals("https://api.weather.gov/gridpoints/PUB/33,107/forecast", forecast.getUrl());
	}
	
	@Test
	void test_Forecast_Point_mapping() {
		assertNotNull(forecast);
		assertNotNull(forecast.getPointList());
		assertTrue(forecast.getPointList().size() > 0);
		assertEquals(1, forecast.getPointList().get(0).getId());
		assertEquals("https://api.weather.gov/points/39.1177,-106.4453", forecast.getPointList().get(0).getIdUrl());
	}
	
	@Test
	void test_Forecast_Geometry_mapping() {
		assertNotNull(forecast);
		assertNotNull(forecast.getGeometry());
		assertEquals(3, forecast.getGeometry().getId());
		assertEquals(5, forecast.getGeometry().getCoordinateList().size());
		assertEquals("Polygon", forecast.getGeometry().getType());
		assertFalse(forecast.isHourly());
		forecast = em.find(Forecast.class, 2);
		assertNotNull(forecast.getGeometry());
		assertEquals(4, forecast.getGeometry().getId());
		assertEquals(5, forecast.getGeometry().getCoordinateList().size());
		assertEquals("Polygon", forecast.getGeometry().getType());
		assertTrue(forecast.isHourly());
	}
	
	@Test
	void test_Forecast_Period_mapping() {
		assertNotNull(forecast);
		assertNotNull(forecast.getPeriodList());
		assertTrue(forecast.getPeriodList().size() > 0);
		assertTrue(forecast.getPeriodList().size() == 14);
		assertEquals(1, forecast.getPeriodList().get(0).getId());
		assertEquals(forecast, forecast.getPeriodList().get(0).getForecast());
		forecast = em.find(Forecast.class, 2);
		assertNotNull(forecast);
		assertNotNull(forecast.getPeriodList());
		assertTrue(forecast.getPeriodList().size() > 0);
		assertTrue(forecast.getPeriodList().size() == 156);
		assertEquals(15, forecast.getPeriodList().get(0).getId());
		assertEquals(forecast, forecast.getPeriodList().get(0).getForecast());
	}

}
