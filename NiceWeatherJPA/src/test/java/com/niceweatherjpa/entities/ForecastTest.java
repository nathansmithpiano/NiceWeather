package com.niceweatherjpa.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

class ForecastTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Forecast forecast1;
	private Forecast forecast2;
	
	// Settings
	private int forecast1Id = 1;
	private String forecast1Url = "https://api.weather.gov/gridpoints/PUB/33,107/forecast";
	private int forecast1PointId = 1;
	private int forecast1GeometryId = 831;
	private int forecast1PeriodCount = 14;
	private int forecast2Id = 2;
	private String forecast2Url = "https://api.weather.gov/gridpoints/PUB/33,107/forecast/hourly";
	private int forecast2PointId = 1;
	private int forecast2GeometryId = 832;
	private int forecast2PeriodCount = 156;
	private int numGeometryCoordinates = 5;
	private String geometryType = "Polygon";
	private String pointUrl = "https://api.weather.gov/points/39.1177,-106.4453";
	
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
		forecast1 = em.find(Forecast.class, forecast1Id);
		assertNotNull(forecast1);
		forecast2 = em.find(Forecast.class, forecast2Id);
		assertNotNull(forecast2);
	}
	
	@AfterEach
	void tearDown() {
		em.close();
		forecast1 = null;
		forecast2 = null;
	}

	@Test
	@DisplayName("Forecast mapping")
	void test_Forecast_mapping() {
		assertEquals(forecast1Id, forecast1.getId());
		assertEquals(forecast1Url, forecast1.getUrl());
		assertFalse(forecast1.isHourly());
		assertEquals(forecast2Id, forecast2.getId());
		assertEquals(forecast2Url, forecast2.getUrl());
		assertTrue(forecast2.isHourly());
	}
	
	@Test
	@DisplayName("Forecast Point mapping")
	void test_Forecast_Point_mapping() {
		Point forecast1Point = forecast1.getPoint();
		assertNotNull(forecast1Point);
		assertEquals(forecast1PointId, forecast1Point.getId());
		assertEquals(pointUrl, forecast1Point.getIdUrl());
		Point forecast2Point = forecast2.getPoint();
		assertNotNull(forecast2Point);
		assertEquals(forecast2PointId, forecast2Point.getId());
		assertEquals(pointUrl, forecast2Point.getIdUrl());
	}
	
	@Test
	@DisplayName("Forecast Geometry mapping")
	void test_Forecast_Geometry_mapping() {
		// Verify forecast1
		Geometry forecast1Geometry = forecast1.getGeometry();
		assertNotNull(forecast1Geometry);
		assertEquals(forecast1GeometryId, forecast1Geometry.getId());
		assertEquals(numGeometryCoordinates, forecast1Geometry.getCoordinates().size());
		assertEquals(geometryType, forecast1Geometry.getType());
		
		// Verify forecast2
		Geometry forecast2Geometry = forecast2.getGeometry();
		assertNotNull(forecast2Geometry);
		assertEquals(forecast2GeometryId, forecast2Geometry.getId());
		assertEquals(numGeometryCoordinates, forecast2Geometry.getCoordinates().size());
		assertEquals(geometryType, forecast2Geometry.getType());
	}
	
	@Test
	@DisplayName("Forecast multiple Period mapping")
	void test_Forecast_Period_mapping() {
		// Verify forecast1
		Set<Period> forecast1Periods = forecast1.getPeriods();
		assertNotNull(forecast1Periods);
		assertTrue(forecast1Periods.size() > 0);
		assertEquals(forecast1PeriodCount, forecast1Periods.size());
		// Verify first period is number 1
		assertEquals(1, forecast1Periods.iterator().next().getNumber());
		
		// Verify forecast1
		Set<Period> forecast2Periods = forecast2.getPeriods();
		assertNotNull(forecast2Periods);
		assertTrue(forecast2Periods.size() > 0);
		assertEquals(forecast2PeriodCount, forecast2Periods.size());
		// Verify first period is number 1
		assertEquals(1, forecast2Periods.iterator().next().getNumber());
	}

}
