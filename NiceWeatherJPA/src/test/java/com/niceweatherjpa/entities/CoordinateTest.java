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

class CoordinateTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Coordinate coordinate;
	
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
		coordinate = em.find(Coordinate.class, 1);
	}
	
	@AfterEach
	void tearDown() {
		em.close();
		coordinate = null;
	}

	@Test
	void test_Coordinate_mapping() {
		assertNotNull(coordinate);
		assertEquals(1, coordinate.getId());
		assertEquals(39.11771, coordinate.getLatitude());
		assertEquals(-106.445335, coordinate.getLongitude());
	}
	
	@Test
	void test_Coordinate_Location_mapping() {
		assertNotNull(coordinate);
		assertNotNull(coordinate.getLocationList());
		assertTrue(coordinate.getLocationList().size() > 0);
		assertEquals(1, coordinate.getLocationList().get(0).getId());
		assertEquals("Mt. Elbert", coordinate.getLocationList().get(0).getName());
	}
	
	@Test
	void test_Coordinate_Geometry_mapping() {
		assertNotNull(coordinate);
		assertNull(coordinate.getGeometry());
		coordinate = em.find(Coordinate.class, 2);
		assertNotNull(coordinate.getGeometry());
		assertEquals(1, coordinate.getGeometry().getId());
		assertEquals("Point", coordinate.getGeometry().getType());
	}

}
