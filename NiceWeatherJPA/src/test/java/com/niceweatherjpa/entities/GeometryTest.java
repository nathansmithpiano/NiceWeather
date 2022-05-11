package com.niceweatherjpa.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GeometryTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Geometry geometry;
	
	// Settings
	private final int geometryId = 1;
	private final int geometry1CoordinateCount = 1;
	private final int geometry1CoordinateId = 1;
	private final double geometry1CoordinateLatitude = 39.11771;
	private final double geometry1CoordinateLongitude = -106.445335;
	private final int geometry1LocationId = 1;
	private final String geometry1LocationName = "Mt. Elbert";
	private final int geometryWithMultipleCoordinatesId = 830;
	private final String geometryWithMultipleCoordinatesType = "Polygon";
	private final int geometryWithMultipleCoordinatesCoordinateCount = 5;
	private final int geometryWithRelativeLocationId = 829;
	private final int geometryWithPointId = 828;
	private final int pointId = 1;
	private final String pointIdUrl = "https://api.weather.gov/points/39.1177,-106.4453";
	private final int geometryWithForecastsId = 831;
	private final int forecastId = 1;
	private final String forecastUrl = "https://api.weather.gov/gridpoints/PUB/33,107/forecast";
	
	
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
		geometry = em.find(Geometry.class, geometryId);
		assertNotNull(geometry);
		assertEquals(geometryId, geometry.getId());
	}
	
	@AfterEach
	void tearDown() {
		em.close();
		geometry = null;
	}

	@Test
	@DisplayName("Geometry mapping")
	void test_Geometry_mapping() {
		assertEquals("Point", geometry.getType());
	}
	
	@Test
	@DisplayName("Geometry single Coordinate mapping")
	void test_Geometry_Coordinate_mapping() {
		assertNotNull(geometry.getCoordinates());
		assertTrue(geometry.getCoordinates().size() > 0);
		assertEquals(geometry1CoordinateCount, geometry.getCoordinates().size());
		
		// Get Geometry's first Coordinate
		Coordinate coordinate = geometry.getCoordinates().iterator().next();
		assertNotNull(coordinate);
		assertEquals(geometry1CoordinateId, coordinate.getId());
		assertEquals(geometry1CoordinateLatitude, coordinate.getLatitude());
		assertEquals(geometry1CoordinateLongitude, coordinate.getLongitude());
		// TODO: multiple coordinate geometry test
	}
	
	@Test
	@DisplayName("Geometry multiple Coordinate mapping")
	void test_Geometry_multiple_Coordinate_mapping() {
		Geometry mGeo = em.find(Geometry.class, geometryWithMultipleCoordinatesId);
		assertNotNull(mGeo);
		assertEquals(geometryWithMultipleCoordinatesType, mGeo.getType());
		assertTrue(mGeo.getCoordinates().size() > 0);
		assertEquals(geometryWithMultipleCoordinatesCoordinateCount, mGeo.getCoordinates().size());
	}
	
	@Test
	@DisplayName("Geometry Location mapping")
	void test_Geometry_Location_mapping() {
		assertNotNull(geometry.getLocation());
		assertEquals(geometry1LocationId, geometry.getLocation().getId());
		assertEquals(geometry1LocationName, geometry.getLocation().getName());
	}
	
	@Test
	@DisplayName("Geometry multiple RelativeLocation mapping")
	void test_Geometry_multiple_RelativeLocation_mapping() {
		geometry = em.find(Geometry.class, geometryWithRelativeLocationId);
		assertNotNull(geometry);
		Set<RelativeLocation> relativeLocations = geometry.getRelativeLocations();
		assertNotNull(relativeLocations);
		assertTrue(relativeLocations.size() > 0);
		
		//Verify Geometry within RelativeLocation's Geometry
		boolean found = false;
		for (RelativeLocation rLoc : relativeLocations) {
			if (rLoc.getGeometry() == geometry) {
				found = true;
			}
		}
		assertTrue(found);
	}
	
	@Test
	@DisplayName("Geometry Point mapping")
	void test_Geometry_Point_mapping() {
		geometry = em.find(Geometry.class, geometryWithPointId);
		assertNotNull(geometry);
		assertNotNull(geometry.getPoint());
		assertEquals(pointId, geometry.getPoint().getId());
		assertEquals(pointIdUrl, geometry.getPoint().getIdUrl());
	}
	
	@Test
	@DisplayName("Geometry multiple Forecast mapping")
	void test_Geometry_Forecast_mapping() {
		geometry = em.find(Geometry.class, geometryWithForecastsId);
		assertNotNull(geometry);
		assertNotNull(geometry.getForecasts());
		assertTrue(geometry.getForecasts().size() > 0);
		
		// Get first Forecast
		Forecast forecast = geometry.getForecasts().iterator().next();
		assertNotNull(forecast);
		assertEquals(forecastId, forecast.getId());
		assertEquals(forecastUrl, forecast.getUrl());
		geometry = em.find(Geometry.class, 4);
	}
	
}
