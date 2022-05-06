package com.niceweatherjpa.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PointTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Point point;
	
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
		point = em.find(Point.class, 1);
	}
	
	@AfterEach
	void tearDown() {
		em.close();
		point = null;
	}

	@Test
	void test_Point_mapping() {
		assertNotNull(point);
		assertEquals(1, point.getId());
		assertEquals("https://api.weather.gov/points/39.1177,-106.4453", point.getIdUrl());
	}
	
	@Test
	void test_Point_Location_mapping() {
		assertNotNull(point);
		assertNotNull(point.getLocation());
		assertEquals(1, point.getLocation().getId());
		assertEquals("Mt. Elbert", point.getLocation().getName());
	}
	
	@Test
	void test_Point_RelativeLocation_mapping() {
		assertNotNull(point);
		assertNotNull(point.getRelativeLocation());
		assertEquals(1, point.getRelativeLocation().getId());
		assertEquals("Twin Lakes", point.getRelativeLocation().getCity());
	}
	
	@Test
	void test_Point_Geometry_mapping() {
		assertNotNull(point);
		assertNotNull(point.getGeometry());
		assertEquals(1, point.getGeometry().getId());
		assertEquals("Point", point.getGeometry().getType());
	}
	
	@Test
	void test_Point_Forecast_mapping() {
		assertNotNull(point);
		assertNotNull(point.getForecastList());
		assertTrue(point.getForecastList().size() > 0);
		assertNotNull(point.getForecastList().get(0));
		assertEquals(1, point.getForecastList().get(0).getId());
		assertEquals("https://api.weather.gov/gridpoints/PUB/33,107/forecast", point.getForecastList().get(0).getUrl());
		assertNotNull(point.getForecastList().get(1));
		assertEquals(2, point.getForecastList().get(1).getId());
		assertEquals("https://api.weather.gov/gridpoints/PUB/33,107/forecast/hourly", point.getForecastList().get(1).getUrl());
	}

}
