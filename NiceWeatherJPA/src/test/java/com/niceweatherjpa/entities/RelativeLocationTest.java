package com.niceweatherjpa.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RelativeLocationTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private RelativeLocation rLoc;
	
	// Settings
	private final int rLocId = 1;
	private final String rLocCity = "Twin Lakes";
	private final int geometryId = 829;
	private final int geometryCoordinatesCount = 1;
	private final int pointId = 1;
	
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
		rLoc = em.find(RelativeLocation.class, rLocId);
		assertNotNull(rLoc);
	}
	
	@AfterEach
	void tearDown() {
		em.close();
		rLoc = null;
	}

	@Test
	void test_RelativeLocation_mapping() {
		assertEquals(rLocId, rLoc.getId());
		assertEquals(rLocCity, rLoc.getCity());
	}
	
	@Test
	void test_RelativeLocation_Geometry_mapping() {
		Geometry geometry = rLoc.getGeometry();
		assertNotNull(geometry);
		assertEquals(geometryId, geometry.getId());
		assertEquals(geometryCoordinatesCount, geometry.getCoordinates().size());
	}
	
	@Test
	void test_RelativeLocation_Point_mapping() {
		List<Point> points = rLoc.getPoints();
		assertNotNull(points);
		assertTrue(points.size() > 0);
		
		// Verify contains point 1 (Mt. Elbert)
		Point point = em.find(Point.class, pointId);
		assertNotNull(point);
		assertTrue(points.contains(point));
	}

}
