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
	
	// Settings
	private final int coordinateId = 1;
	private final double latitude = 39.11771;
	private final double longitude = -106.445335;
	private final int coordinateGeometryId = 1;
	private final String coordinateGeometryType = "Point";
	
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
		assertEquals(coordinateId, coordinate.getId());
		assertEquals(latitude, coordinate.getLatitude());
		assertEquals(longitude, coordinate.getLongitude());
	}
	
	@Test
	void test_Coordinate_Geometry_mapping() {
		assertNotNull(coordinate);
		assertNotNull(coordinate.getGeometry());
		coordinate = em.find(Coordinate.class, coordinateId);
		assertNotNull(coordinate.getGeometry());
		assertEquals(coordinateGeometryId, coordinate.getGeometry().getId());
		assertEquals(coordinateGeometryType, coordinate.getGeometry().getType());
	}

}
