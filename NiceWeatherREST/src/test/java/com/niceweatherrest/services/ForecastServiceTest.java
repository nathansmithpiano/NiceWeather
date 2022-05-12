package com.niceweatherrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.niceweatherjpa.entities.Coordinate;
import com.niceweatherjpa.entities.Forecast;
import com.niceweatherjpa.entities.Geometry;
import com.niceweatherjpa.entities.Period;
import com.niceweatherjpa.entities.Point;

@SpringBootTest
class ForecastServiceTest {

	@Autowired
	private ForecastServiceImpl forcSvc;
	
	@Autowired
	private PeriodServiceImpl periodSvc;
	
	@Autowired
	private PointServiceImpl pointSvc;
	
	@Autowired
	private GeometryServiceImpl geoSvc;
	
	@Autowired
	private CoordinateServiceImpl coordSvc;
	
	// Settings
	private final int forecastId = 1;
	private final int periodId = 1;
	private final int pointId = 1;
	
	@BeforeEach
	void setUp() throws Exception {
		assertNotNull(periodSvc);
		assertNotNull(forcSvc);
		assertNotNull(pointSvc);
		assertNotNull(geoSvc);
		assertNotNull(coordSvc);
	}

	@AfterEach
	void tearDown() throws Exception {
		periodSvc = null;
		forcSvc = null;
		pointSvc = null;
		geoSvc = null;
		coordSvc = null;
	}
	
	@Test
	@DisplayName("ForecastService index() and count()")
	void test_index() {
		assertNotNull(forcSvc.index());
		assertTrue(forcSvc.count() > 0);
	}
	
//	@Test
//	@DisplayName("ForecastService update()")
//	void test_update() {
//		// Update and restore name
//		
//		// Settings
//		final String updatedType = "Updated Type";
//		final int forecastCount = forcSvc.index().size();
//		
//		// Find in DB
//		Forecast forecast = forcSvc.findById(forecastId);
//		assertNotNull(forecast);
//		final String initialType = forecast.getType();
//		
//		// Change locally
//		forecast.setType(updatedType);
//		
//		// Update on DB
//		Forecast updatedForecast = forcSvc.update(forecast);
//		assertNotNull(updatedForecast);
//		forecast = null;
//		Forecast foundForecast = forcSvc.findById(forecastId);
//		assertNotNull(foundForecast);
//		updatedForecast = null;
//		
//		// Verify
//		assertEquals(forecastId, foundForecast.getId());
//		assertEquals(updatedType, foundForecast.getType());
//		
//		// Revert locally
//		foundForecast.setType(initialType);
//		
//		// Revert on DB
//		Forecast revertedForecast = forcSvc.update(foundForecast);
//		assertNotNull(revertedForecast);
//		foundForecast = null;
//		
//		// Verify
//		assertEquals(forecastId, revertedForecast.getId());
//		assertEquals(initialType, revertedForecast.getType());
//		
//		// Verify nothing new created
//		assertEquals(forecastCount, forcSvc.index().size());
//	}
	
	@Test
	@DisplayName("ForecastService CRUD with many Periods, 1 Geometry, and many Coordinates")
	void test_CRUD_with_Period_Geometry_Coordinate() {
		// 1: Create Forecast with many Periods, 1 Geometry, and many Coordinates and verify
		// 2: Update Forecast's type, Geometry's type, and Coordinate latitude and longitude and verify
		// 3: Delete all and verify
		
		// Settings
		final int forecastCountInitial = forcSvc.index().size();
		final int pointCountInitial = forcSvc.index().size();
		final int periodCountInitial = periodSvc.index().size();
		final int geometryCountInitial = geoSvc.index().size();
		final int coordinateCount = coordSvc.index().size();
		final int numGeometryCoordinates = 5;
		final int numNewPeriods = 2;
		final String typeInitial = "New Type";
		final String periodName = "New Name";
		
		// ******
		// STEP 1
		// ******
		
		// 1.1: Create Forecast locally
		Forecast forecast = new Forecast();
		forecast.setType(typeInitial);
		
		// Find Point in DB
		Point point = pointSvc.findById(pointId);
		assertNotNull(point);
		final int pointForecastCount = point.getForecasts().size();
		
		// Set Point to Forecast
		forecast.setPoint(point);
		
		// Create 5 new Coordinates for Forecast's Geometry
		Coordinate coordinate1 = new Coordinate();
		coordinate1.setLatitude(111.11);
		coordinate1.setLongitude(-111.11);
		Coordinate coordinate2 = new Coordinate();
		coordinate2.setLatitude(222.22);
		coordinate2.setLongitude(-222.22);
		Coordinate coordinate3 = new Coordinate();
		coordinate3.setLatitude(333.33);
		coordinate3.setLongitude(-333.33);
		Coordinate coordinate4 = new Coordinate();
		coordinate4.setLatitude(444.44);
		coordinate4.setLongitude(-444.44);
		Coordinate coordinate5 = new Coordinate();
		coordinate5.setLatitude(555.55);
		coordinate5.setLongitude(-555.55);
		
		// Create new Geometry for Forecast
		Geometry geometry = new Geometry();
		
		// Add Coordinates to Geometry
		geometry.addCoordinate(coordinate1);
		geometry.addCoordinate(coordinate2);
		geometry.addCoordinate(coordinate3);
		geometry.addCoordinate(coordinate4);
		geometry.addCoordinate(coordinate5);
		
		// Set Geometry to Forecast
		forecast.setGeometry(geometry);
		
		// Create new Periods
		Period period1 = new Period();
		period1.setName(periodName + " 1");
		forecast.addPeriod(period1);
		Period period2 = new Period();
		period2.setName(periodName + " 2");
		forecast.addPeriod(period2);
		assertEquals(numNewPeriods, forecast.getPeriods().size());
		
		// Persist in DB
		Forecast createdForecast = forcSvc.create(forecast);
		assertNotNull(createdForecast);
		final int createdId = createdForecast.getId();
		createdForecast = null;
		Forecast foundForecast = forcSvc.findById(createdId);
		assertNotNull(foundForecast);
		
		// Verify Forecast
		assertEquals(typeInitial, foundForecast.getType());
		
		// Find new Periods in DB and verify
		assertEquals(numNewPeriods, foundForecast.getPeriods().size());
		List<Integer> periodIds = new ArrayList<>();
		for (Period period : foundForecast.getPeriods()) {
			assertNotNull(period);
			assertEquals(foundForecast, period.getForecast());
			periodIds.add(period.getId());
		}
		
		// Find updated Point in DB
		Point updatedPoint = pointSvc.findById(pointId);
		assertNotNull(updatedPoint);
		point = null;
		
		// Verify Point
		assertEquals(updatedPoint, foundForecast.getPoint());
		assertTrue(updatedPoint.getForecasts().contains(foundForecast));
		
		// Find updated Geometry in Forecast
		Geometry updatedGeometry = forecast.getGeometry();
		assertNotNull(updatedGeometry);
		final int geometryId = updatedGeometry.getId();
		Geometry foundGeometry = geoSvc.findById(geometryId);
		assertNotNull(foundGeometry);
		updatedGeometry = null;
		
		// Verify Geometry
		assertEquals(foundGeometry, foundForecast.getGeometry());
		assertTrue(foundGeometry.getForecasts().contains(foundForecast));
		
		// Verify Coordinates
		assertEquals(numGeometryCoordinates, foundGeometry.getCoordinates().size());
		assertTrue(foundGeometry.getCoordinates().contains(coordinate1));
		assertTrue(foundGeometry.getCoordinates().contains(coordinate2));
		assertTrue(foundGeometry.getCoordinates().contains(coordinate3));
		assertTrue(foundGeometry.getCoordinates().contains(coordinate4));
		assertTrue(foundGeometry.getCoordinates().contains(coordinate5));
		assertEquals(foundGeometry, coordinate1.getGeometry());
		assertEquals(foundGeometry, coordinate2.getGeometry());
		assertEquals(foundGeometry, coordinate3.getGeometry());
		assertEquals(foundGeometry, coordinate4.getGeometry());
		assertEquals(foundGeometry, coordinate5.getGeometry());
		
		// Delete on DB
		assertTrue(forcSvc.deleteById(createdId));
		assertNull(forcSvc.findById(createdId));
		
		// Verify Point unchanged
		Point foundPoint = pointSvc.findById(pointId);
		assertNotNull(foundPoint);
		assertFalse(foundPoint.getForecasts().contains(foundForecast));
		
		// Verify Geometry deleted
		assertNull(geoSvc.findById(geometryId));
		
		// Verify Coordinates deleted
		for (Coordinate coordinate : foundGeometry.getCoordinates()) {
			assertNull(coordSvc.findById(coordinate.getId()));
		}
		
		// Verify Periods deleted
		for (Integer id : periodIds) {
			assertNull(periodSvc.findById(id));
		}
		
		// Verify nothing new remains
		assertEquals(forecastCountInitial, forcSvc.index().size());
		assertEquals(geometryCountInitial, geoSvc.index().size());
		assertEquals(coordinateCount, coordSvc.index().size());
		assertEquals(pointCountInitial, pointSvc.index().size());
		assertEquals(periodCountInitial, periodSvc.index().size());
	}

	

}
