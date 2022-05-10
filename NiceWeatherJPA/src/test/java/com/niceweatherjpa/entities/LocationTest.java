package com.niceweatherjpa.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LocationTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Location location;
	
	// Settings
	private final int locationId = 1;
	private final String locationName = "Mt. Elbert";
	private final int rangeId = 6;
	private final String rangeName = "Sawatch";
	private final int category1Id = 1;
	private final String category1Name = "Peak";
	private final int category2Id = 3;
	private final String category2Name = "Colorado 14er";
	private final int geometryId = 1;
	private final int numCoordinates = 1;
	private final double latitude = 39.11771;
	private final double longitude = -106.445335;
	private final int pointId = 1;
	private final String pointUrl = "https://api.weather.gov/points/39.1177,-106.4453";

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
	@DisplayName("Location mapping")
	void test_Location_mapping() {
		assertNotNull(location);
		assertEquals(locationId, location.getId());
		assertEquals(locationName, location.getName());
	}
	
	@Test
	@DisplayName("Location MountainRange mapping")
	void test_Location_MountainRange_mapping() {
		assertNotNull(location);
		assertNotNull(location.getMountainRange());
		assertEquals(rangeId, location.getMountainRange().getId());
		assertEquals(rangeName, location.getMountainRange().getName());
	}

	@Test
	@DisplayName("Location multiple Category mapping")
	void test_Location_Category_mapping() {
		assertNotNull(location);
		assertNotNull(location.getCategories());
		assertTrue(location.getCategories().size() > 0);
		
		// Get Location's first two categories
		Iterator<Category> it = location.getCategories().iterator();
		assertNotNull(it);
		assertTrue(it.hasNext());
		Category category1 = it.next();
		assertNotNull(category1);
		Category category2 = it.next();
		assertNotNull(category2);
		
		// Verify Location's first two categories
		assertNotEquals(category1, category2);
		assertEquals(category1Id, category1.getId());
		assertEquals(category1Name, category1.getName());
		assertEquals(category2Id, category2.getId());
		assertEquals(category2Name, category2.getName());
	}
	
	@Test
	@DisplayName("Location Geometry mapping")
	void test_Location_Geometry_mapping() {
		assertNotNull(location);
		assertNotNull(location.getGeometry());
		assertEquals(geometryId, location.getGeometry().getId());
	}
	
	@Test
	@DisplayName("Location Geometry-Coordinate mapping")
	void test_Location_Geometry_Coordinate_mapping() {
		assertNotNull(location);
		assertNotNull(location.getGeometry());
		
		// Get Geometry's Coordinate
		assertTrue(location.getGeometry().getCoordinates().size() == numCoordinates);
		Coordinate coordinate = location.getGeometry().getCoordinates().iterator().next();
		assertNotNull(coordinate);
		
		// Verify Geometry's Coordinate
		assertEquals(latitude, coordinate.getLatitude());
		assertEquals(longitude, coordinate.getLongitude());
	}
	
	@Test
	@DisplayName("Location Point mapping")
	void test_Location_Point_mapping() {
		assertNotNull(location.getPoint());
		assertEquals(pointId, location.getPoint().getId());
		assertEquals(pointUrl, location.getPoint().getIdUrl());
	}

}
