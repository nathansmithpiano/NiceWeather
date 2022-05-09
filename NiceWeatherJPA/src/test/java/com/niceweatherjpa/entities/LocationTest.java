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
		assertEquals(1, location.getId());
		assertEquals("Mt. Elbert", location.getName());
	}
	
	@Test
	@DisplayName("Location Geometry mapping")
	void test_Location_Geometry_mapping() {
		assertNotNull(location);
		assertNotNull(location.getGeometry());
		assertEquals(1, location.getGeometry().getId());
	}
	
	@Test
	@DisplayName("Location Geometry-Coordinate mapping")
	void test_Location_Geometry_Coordinate_mapping() {
		assertNotNull(location);
		assertNotNull(location.getGeometry());
		
		// Get Geometry's Coordinate
		assertTrue(location.getGeometry().getCoordinates().size() == 1);
		Coordinate coordinate = location.getGeometry().getCoordinates().iterator().next();
		assertNotNull(coordinate);
		
		// Verify Geometry's Coordinate
		assertEquals(39.11771, coordinate.getLatitude());
		assertEquals(-106.445335, coordinate.getLongitude());
	}
	
	@Test
	@DisplayName("Location MountainRange mapping")
	void test_Location_MountainRange_mapping() {
		assertNotNull(location);
		assertNotNull(location.getMountainRange());
		assertEquals(6, location.getMountainRange().getId());
		assertEquals("Sawatch", location.getMountainRange().getName());
	}

	@Test
	@DisplayName("Location Category mapping")
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
		assertEquals(1, category1.getId());
		assertEquals("Peak", category1.getName());
		assertEquals(3, category2.getId());
		assertEquals("Colorado 14er", category2.getName());
	}
	
//	@Test
//	void test_Location_Point_mapping() {
//		assertNotNull(location);
//		assertNotNull(location.getPoint());
//		assertEquals(1, location.getPoint().getId());
//		assertEquals("https://api.weather.gov/points/39.1177,-106.4453", location.getPoint().getIdUrl());
//	}

}
