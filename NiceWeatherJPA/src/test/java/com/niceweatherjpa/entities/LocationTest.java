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

class LocationTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Location location;

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
		location = em.find(Location.class, 1);
	}

	@AfterEach
	void tearDown() {
		em.close();
		location = null;
	}

	@Test
	void test_Location_mapping() {
		assertNotNull(location);
		assertEquals(1, location.getId());
		assertEquals("Mt. Elbert", location.getName());
	}

	@Test
	void test_Location_Category_mapping() {
		assertNotNull(location);
		assertNotNull(location.getCategoryList());
		assertTrue(location.getCategoryList().size() > 0);
		assertEquals(1, location.getCategoryList().get(0).getId());
		assertEquals("Peak", location.getCategoryList().get(0).getName());
		assertEquals(3, location.getCategoryList().get(1).getId());
		assertEquals("Colorado 14er", location.getCategoryList().get(1).getName());
	}
	
	@Test
	void test_Location_Coordinate_mapping() {
		assertNotNull(location);
		assertNotNull(location.getCoordinate());
		assertEquals(1, location.getCoordinate().getId());
		assertEquals(39.11771, location.getCoordinate().getLatitude());
		assertEquals(-106.445335, location.getCoordinate().getLongitude());
	}
	
	@Test
	void test_Location_MountainRange_mapping() {
		assertNotNull(location);
		assertNotNull(location.getMountainRange());
		assertEquals(6, location.getMountainRange().getId());
		assertEquals("Sawatch", location.getMountainRange().getName());
	}
	
	@Test
	void test_Location_Point_mapping() {
		assertNotNull(location);
		assertNotNull(location.getPoint());
		assertEquals(1, location.getPoint().getId());
		assertEquals("https://api.weather.gov/points/39.1177,-106.4453", location.getPoint().getIdUrl());
	}

}
