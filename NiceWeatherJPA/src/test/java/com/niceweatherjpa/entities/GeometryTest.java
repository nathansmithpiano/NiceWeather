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

class GeometryTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Geometry geometry;
	
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
		geometry = em.find(Geometry.class, 1);
	}
	
	@AfterEach
	void tearDown() {
		em.close();
		geometry = null;
	}

	@Test
	void test_Geometry_mapping() {
		assertNotNull(geometry);
		assertEquals(1, geometry.getId());
		assertEquals("Point", geometry.getType());
	}
	
	@Test
	void test_Geometry_Coordinate_mapping() {
		assertNotNull(geometry);
		assertNotNull(geometry.getCoordinateList());
		assertTrue(geometry.getCoordinateList().size() > 0);
		
		// TODO: multiple coordinate geometry test
	}
	
	@Test
	void test_Geometry_Point_mapping() {
		assertNotNull(geometry);
		assertNotNull(geometry.getPoint());
		assertEquals(1, geometry.getPoint().getId());
		assertEquals("https://api.weather.gov/points/39.1177,-106.4453", geometry.getPoint().getIdUrl());
	}
	
	@Test
	void test_Geometry_RelativeLocation_mapping() {
		geometry = em.find(Geometry.class, 2);
		assertNotNull(geometry);
		assertNotNull(geometry.getRelativeLocationList());
		assertTrue(geometry.getRelativeLocationList().size() > 0);
		assertEquals("Twin Lakes", geometry.getRelativeLocationList().get(0).getCity());
	}
	
	@Test
	void test_Geometry_Forecast_mapping() {
		geometry = em.find(Geometry.class, 3);
		assertNotNull(geometry);
		assertNotNull(geometry.getForecastList());
		assertTrue(geometry.getForecastList().size() > 0);
		assertEquals(1, geometry.getForecastList().get(0).getId());
		assertEquals("https://api.weather.gov/gridpoints/PUB/33,107/forecast", geometry.getForecastList().get(0).getUrl());
		geometry = em.find(Geometry.class, 4);
		assertNotNull(geometry);
		assertNotNull(geometry.getForecastList());
		assertTrue(geometry.getForecastList().size() > 0);
		assertEquals(2, geometry.getForecastList().get(0).getId());
		assertEquals("https://api.weather.gov/gridpoints/PUB/33,107/forecast/hourly", geometry.getForecastList().get(0).getUrl());
	}

}
