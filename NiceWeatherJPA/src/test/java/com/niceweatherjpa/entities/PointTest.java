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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PointTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Point point;
	
	// Settings
	final int pointId = 1;
	final String idUrl = "https://api.weather.gov/points/39.1177,-106.4453";
	private final int locationId = 1;
	private final String locationName = "Mt. Elbert";
	final int relativeLocationId = 1;
	final String relativeLocationCity = "Twin Lakes";
	final int geometryId = 828;
	final int geometryCoordinatesCount = 1;
	final int forecastsCount = 2;
	final String forecastUrlNormal = "https://api.weather.gov/gridpoints/PUB/33,107/forecast";
	final String forecastUrlHourly = "https://api.weather.gov/gridpoints/PUB/33,107/forecast/hourly";
	
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
		point = em.find(Point.class, pointId);
		assertNotNull(point);
	}
	
	@AfterEach
	void tearDown() {
		em.close();
		point = null;
	}

	@Test
	@DisplayName("Point mapping")
	void test_Point_mapping() {
		assertEquals(pointId, point.getId());
		assertEquals(idUrl, point.getIdUrl());
	}
	
	@Test
	@DisplayName("Point Location mapping")
	void test_Point_Location_mapping() {
		assertNotNull(point.getLocation());
		assertEquals(locationId, point.getLocation().getId());
		assertEquals(locationName, point.getLocation().getName());
	}
	
	@Test
	@DisplayName("Point RelativeLocation mapping")
	void test_Point_RelativeLocation_mapping() {
		RelativeLocation rloc = point.getRelativeLocation();
		assertNotNull(rloc);
		assertEquals(relativeLocationId, rloc.getId());
		assertEquals(relativeLocationCity, rloc.getCity());
	}
	
	@Test
	@DisplayName("Point Geometry mapping")
	void test_Point_Geometry_mapping() {
		Geometry geometry = point.getGeometry();
		assertNotNull(geometry);
		assertEquals(geometryId, geometry.getId());
		assertEquals(geometryCoordinatesCount, geometry.getCoordinates().size());
	}
	
	@Test
	@DisplayName("Point multiple Forecast mapping")
	void test_Point_Forecast_mapping() {
		List<Forecast> forecasts = point.getForecasts();
		assertNotNull(forecasts);
		assertEquals(forecastsCount, forecasts.size());
		
		// Verify has both normal and hourly forecasts
		// Verify both forecastUrls are found
		boolean normalFound = false;
		boolean hourlyFound = false;
		boolean normalUrlFound = false;
		boolean hourlyUrlFound = false;
		for (Forecast fc : forecasts) {
			if (!fc.isHourly()) {
				normalFound = true;
				if (fc.getUrl().equals(forecastUrlNormal)) {
					normalUrlFound = true;
				}
			}
			if (fc.isHourly()) {
				hourlyFound = true;
				if (fc.getUrl().equals(forecastUrlHourly)) {
					hourlyUrlFound = true;
				}
			}
		}
		assertTrue(normalFound);
		assertTrue(hourlyFound);
		assertTrue(normalUrlFound);
		assertTrue(hourlyUrlFound);
	}

}
