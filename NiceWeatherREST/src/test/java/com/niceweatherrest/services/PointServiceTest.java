package com.niceweatherrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.niceweatherjpa.entities.Location;
import com.niceweatherjpa.entities.Period;
import com.niceweatherjpa.entities.Point;
import com.niceweatherjpa.entities.RelativeLocation;

@SpringBootTest
class PointServiceTest {

	@Autowired
	private PointServiceImpl pointSvc;

	@Autowired
	private LocationServiceImpl locSvc;

	@Autowired
	private CoordinateServiceImpl coordSvc;

	@Autowired
	private CategoryServiceImpl catSvc;

	@Autowired
	private MountainRangeService mrSvc;

	@Autowired
	private GeometryServiceImpl geoSvc;

	@Autowired
	private RelativeLocationServiceImpl rlSvc;

	@Autowired
	private ForecastServiceImpl forcSvc;

	@Autowired
	private PeriodServiceImpl periodSvc;

	@BeforeEach
	void setUp() throws Exception {
		assertNotNull(pointSvc);
		assertNotNull(locSvc);
		assertNotNull(coordSvc);
		assertNotNull(catSvc);
		assertNotNull(mrSvc);
		assertNotNull(geoSvc);
		assertNotNull(rlSvc);
		assertNotNull(forcSvc);
		assertNotNull(periodSvc);
	}

	@AfterEach
	void tearDown() throws Exception {
		pointSvc = null;
		locSvc = null;
		coordSvc = null;
		catSvc = null;
		mrSvc = null;
		geoSvc = null;
		rlSvc = null;
		forcSvc = null;
		periodSvc = null;
	}

	@Test
	@DisplayName("PointService index() and count()")
	void test_index() {
		assertNotNull(pointSvc.index());
		assertTrue(pointSvc.count() > 0);
	}

	@Test
	@DisplayName("PointService CRUD with Location, Geometry, Coordinate, RelativeLocation, many Forecasts, and many Periods")
	void test_CRUD_with_Location_Geometry_Coordinate_RelativeLocation_Forecast() {
		// 1: Create new Point with existing Location, new Geometry, Coordinate and
		// RelativeLocation
		// 2: Create many Forecasts, and many Periods, and verify
		// 2: Update Geometry, Coordinate, RelativeLocation, Forecasts, and Periods, and
		// verify
		// 3: Delete all and verify

		// Settings
		final int locationId = 827;
		final String locationName = "Point 13,001";
		final String typeInitial = "Point Type";
		final String typeUpdated = "Updated Point Type";
		final String geoTypeInitial = "Geometry Type";
		final String geoTypeUpdated = "Updated Geometry Type";
		final double latitudeInitial = 111.11;
		final double longitudeInitial = -222.22;
		final double latitudeUpdated = 333.33;
		final double longitudeUpdated = -444.44;
		final String rLocTypeInitial = "Relative Location Type";
		final String rLocTypeUpdated = "Updated Relative Location Type";
		final String rLocGeoTypeInitial = "Relative Location Geometry Type";
		final String rLocGeoTypeUpdated = "Updated Relative Location Geometry Type";
		final double rLocLatitudeInitial = 555.55;
		final double rLocLongitudeInitial = -666.66;
		final double rLocLatitudeUpdated = 777.77;
		final double rLocLongitudeUpdated = -888.88;
		final String forecastTypeInitial = "Forecast Type";
		final String forecastTypeUpdated = "Updated Forecast Type";
		final int numNewForecasts = 2;
		final int numForecastCoordinates = 5;
		final double forecastLatitudeInitial = 123.45;
		final double forecastLongitudeInitial = -543.21;
		final double forecastLatitudeUpdated = 987.65;
		final double forecastLongitudeUpdated = -567.89;
		final String forecastGeoTypeInitial = "Forecast Geometry Type";
		final String forecastGeoTypeUpdated = "Forecast Geometry Type";
		final String periodNameInitial = "Period Name";
		final String periodNameUpdated = "Updated Period Name";
		final int numNewPeriods = 200;

		// Used for verification
		final long locationCountInitial = locSvc.count();
		final long pointCountInitial = pointSvc.count();
		final long geometryCountInitial = geoSvc.count();
		final long coordinateCountInitial = coordSvc.count();
		final long relativeLocationCountInitial = rlSvc.count();
		final long forecastCountInitial = forcSvc.count();
		final long periodCountInitial = periodSvc.count();
		
		// Used for additional verification
		final long locationCountAfterCreation = locationCountInitial;
		final long pointCountAfterCreation = pointCountInitial + 1;
		final long geometryCountAfterCreation = geometryCountInitial + 2 + numNewForecasts;
		final long coordinateCountAfterCreation = coordinateCountInitial + 2 + (numNewForecasts * numForecastCoordinates);
		final long relativeLocationCountAfterCreation = relativeLocationCountInitial + 1;
		final long forecastCountAfterCreation = forecastCountInitial + numNewForecasts;
		final long periodCountAfterCreation = periodCountInitial + (numNewForecasts * numNewPeriods);
		

		// ******
		// STEP 1
		// ******

		// DB 1.1: *RETRIEVE* find Location in DB
		Location foundLocation = locSvc.findById(locationId);

		// 1.1: Verify found Location
		assertNotNull(foundLocation);
		assertEquals(locationName, foundLocation.getName());
		assertNotNull(foundLocation.getGeometry());
		assertNotNull(foundLocation.getGeometry().getCoordinates());
		assertEquals(1, foundLocation.getGeometry().getCoordinates().size());
		assertNotNull(foundLocation.getMountainRange());
		assertNotNull(foundLocation.getCategories());
		assertTrue(foundLocation.getCategories().size() > 0);

		// 1.1: Create Point locally
		Point newPoint = new Point();
		newPoint.setType(typeInitial);

		// 1.1: Set found Location to Point
		newPoint.setLocation(foundLocation);
		
		// 1.1: Verify Point's Location locally
		assertNotNull(newPoint.getLocation());
		assertEquals(locationId, newPoint.getLocation().getId());
		assertEquals(foundLocation, newPoint.getLocation());

		// 1.1: Create Geometry for Point
		Geometry newGeometry = new Geometry();
		newGeometry.setType(geoTypeInitial);

		// 1.1: Create Coordinate for Point's Geometry
		Coordinate newCoordinate = new Coordinate();
		newCoordinate.setLatitude(latitudeInitial);
		newCoordinate.setLongitude(longitudeInitial);

		// 1.1: Add Coordinate to Point's Geometry
		newGeometry.addCoordinate(newCoordinate);
		
		// 1.1: Verify Point's Geometry's Coordinate locally
		assertNotNull(newGeometry.getCoordinates());
		assertEquals(1, newGeometry.getCoordinates().size());
		assertEquals(latitudeInitial, newGeometry.getCoordinates().get(0).getLatitude());
		assertEquals(longitudeInitial, newGeometry.getCoordinates().get(0).getLongitude());

		// 1.1: Set Geometry to Point locally
		newPoint.setGeometry(newGeometry);

		// 1.1: Verify Point's Geometry locally
		assertNotNull(newPoint.getGeometry());
		assertEquals(newPoint, newPoint.getGeometry().getPoint());
		assertEquals(geoTypeInitial, newPoint.getGeometry().getType());

		// 1.1: Verify Point's Geometry's Coordinate locally
		assertNotNull(newPoint.getGeometry().getCoordinates());
		assertTrue(newPoint.getGeometry().getCoordinates().size() > 0);
		assertEquals(1, newPoint.getGeometry().getCoordinates().size());
		assertTrue(newPoint.getGeometry().getCoordinates().contains(newCoordinate));
		assertEquals(latitudeInitial, newPoint.getGeometry().getCoordinates().get(0).getLatitude());
		assertEquals(longitudeInitial, newPoint.getGeometry().getCoordinates().get(0).getLongitude());

		// 1.1: Create RelativeLocation for Point
		RelativeLocation newRLoc = new RelativeLocation();
		newRLoc.setType(rLocTypeInitial);

		// 1.1: Create Geometry for Point's RelativeLocation
		Geometry newRLocGeometry = new Geometry();
		newRLocGeometry.setType(rLocGeoTypeInitial);

		// 1.1: Create Coordinate for Point's RelativeLocation's Geometry
		Coordinate newRLocGeoCoordinate = new Coordinate();
		newRLocGeoCoordinate.setLatitude(rLocLatitudeInitial);
		newRLocGeoCoordinate.setLongitude(rLocLongitudeInitial);

		// 1.1: Add Coordinate to Point's RelativeLocation's Geometry
		newRLocGeometry.addCoordinate(newRLocGeoCoordinate);
		
		// 1.1: Verify Point's RelativeLocation's Geometry's Coordinate locally
		assertNotNull(newRLocGeometry.getCoordinates());
		assertEquals(1, newRLocGeometry.getCoordinates().size());
		assertEquals(rLocLatitudeInitial, newRLocGeometry.getCoordinates().get(0).getLatitude());
		assertEquals(rLocLongitudeInitial, newRLocGeometry.getCoordinates().get(0).getLongitude());

		// 1.1: Set Geometry to Point's RelativeLocation
		newRLoc.setGeometry(newRLocGeometry);
		
		// 1.1: Verify Point's RelativeLocation's Geometry locally
		assertNotNull(newRLoc.getGeometry());
		assertEquals(newRLocGeometry, newRLoc.getGeometry());
		assertEquals(rLocGeoTypeInitial, newRLoc.getGeometry().getType());

		// 1.1: Set RelativeLocation to Point
		newPoint.setRelativeLocation(newRLoc);

		// 1.1: Verify Point's RelativeLocation locally
		assertNotNull(newPoint.getRelativeLocation());
		assertNotNull(newPoint.getRelativeLocation().getPoints());
		assertTrue(newPoint.getRelativeLocation().getPoints().size() > 0);
		assertEquals(1, newPoint.getRelativeLocation().getPoints().size());
		assertTrue(newPoint.getRelativeLocation().getPoints().contains(newPoint));
		assertEquals(rLocTypeInitial, newPoint.getRelativeLocation().getType());

		// 1.1: Verify RelativeLocation's Geometry locally
		assertNotNull(newPoint.getRelativeLocation().getGeometry());
		assertEquals(newRLocGeometry, newPoint.getRelativeLocation().getGeometry());
		assertEquals(rLocGeoTypeInitial, newPoint.getRelativeLocation().getGeometry().getType());

		// 1.1: Verify RelativeLocation's Geometry's Coordinate locally
		assertNotNull(newPoint.getRelativeLocation().getGeometry().getCoordinates());
		assertTrue(newPoint.getRelativeLocation().getGeometry().getCoordinates().size() > 0);
		assertEquals(1, newPoint.getRelativeLocation().getGeometry().getCoordinates().size());
		assertTrue(newPoint.getRelativeLocation().getGeometry().getCoordinates().contains(newRLocGeoCoordinate));
		assertEquals(rLocLatitudeInitial, newPoint.getRelativeLocation().getGeometry().getCoordinates().get(0).getLatitude());
		assertEquals(rLocLongitudeInitial, newPoint.getRelativeLocation().getGeometry().getCoordinates().get(0).getLongitude());

		// 1.1: Create Forecasts for Point
		for (int i = 0; i < numNewForecasts; i++) {
			Forecast forecast = new Forecast();
			forecast.setType(forecastTypeInitial);
			
			// 1.1: Create Geometry for Point's Forecasts
			Geometry geometry = new Geometry();
			geometry.setType(forecastGeoTypeInitial);
			
			// 1.1: Create Coordinates for Point's Forecasts' Geometry
			for (int j = 0; j < numForecastCoordinates; j++) {
				Coordinate coordinate = new Coordinate();
				coordinate.setLatitude(forecastLatitudeInitial);
				coordinate.setLongitude(forecastLongitudeInitial);
				
				// 1.1: Add Coordinate to Point's Forecasts' Geometry
				geometry.addCoordinate(coordinate);
				
				// 1.1: Verify Point's Forecasts' Geometry's Coordinate
				assertNotNull(geometry.getCoordinates());
				assertTrue(geometry.getCoordinates().size() > 0);
				assertTrue(geometry.getCoordinates().contains(coordinate));
				assertEquals(forecastLatitudeInitial, geometry.getCoordinates().get(0).getLatitude());
				assertEquals(forecastLongitudeInitial, geometry.getCoordinates().get(0).getLongitude());
			}
			
			// 1.1: Set Geometry to Point's Forecasts'
			forecast.setGeometry(geometry);
			
			// 1.1: Verify Point's Forecasts' Geometry
			assertNotNull(forecast.getGeometry());
			assertEquals(geometry, forecast.getGeometry());
			assertEquals(forecastGeoTypeInitial, forecast.getGeometry().getType());
			
			// 1.1: Verify Point's Forecasts' Geometry's Coordinate locally
			assertNotNull(forecast.getGeometry().getCoordinates());
			assertEquals(numForecastCoordinates, forecast.getGeometry().getCoordinates().size());
			assertEquals(forecastLatitudeInitial, forecast.getGeometry().getCoordinates().get(0).getLatitude());
			assertEquals(forecastLongitudeInitial, forecast.getGeometry().getCoordinates().get(0).getLongitude());
			
			// 1.1: Create Periods for Point's Forecasts
			for (int k = 0; k < numNewPeriods; k++) {
				Period period = new Period();
				period.setName(periodNameInitial);
				period.setNumber(k + 1);
				
				// 1.1: Add Period to Forecast's Period
				forecast.addPeriod(period);
				
				// 1.1: Verify Forecast's Periods
				assertNotNull(forecast.getPeriods());
				assertTrue(forecast.getPeriods().size() > 0);
				assertTrue(forecast.getPeriods().contains(period));
				assertEquals(periodNameInitial, forecast.getPeriods().get(0).getName());
			}
			
			// Add Forecast to Point's Forecasts
			newPoint.addForecast(forecast);
			
			// Verify new Forecast
			assertNotNull(newPoint.getForecasts());
			assertTrue(newPoint.getForecasts().size() > 0);
			assertTrue(newPoint.getForecasts().contains(forecast));
			assertEquals(forecastTypeInitial, newPoint.getForecasts().get(i).getType());
		}

		// 1.1: Verify Point's Forecasts locally
		assertNotNull(newPoint.getForecasts());
		assertTrue(newPoint.getForecasts().size() > 0);
		assertEquals(numNewForecasts, newPoint.getForecasts().size());
		for (Forecast forecast : newPoint.getForecasts()) {
			assertNotNull(forecast);
			assertEquals(newPoint, forecast.getPoint());
			assertEquals(typeInitial, forecast.getPoint().getType());
			assertEquals(forecastTypeInitial, forecast.getType());
			
			// 1.1: Verify Point's Forecasts' Periods locally
			for (Period period : forecast.getPeriods()) {
				assertNotNull(period);
				assertEquals(forecast, period.getForecast());
				assertEquals(forecastTypeInitial, period.getForecast().getType());
			}
		}

		// DB 1.1: *CREATE* Point, RelativeLocation, Geometry, Coordinate, Forecasts,
		// and Periods in DB
		Point createdPoint = pointSvc.create(newPoint);

		// No longer needed, set initial objects to null
		foundLocation = null;
		newPoint = null;
		newRLoc = null;
		newGeometry = null;
		newCoordinate = null;
		newRLocGeometry = null;
		newRLocGeoCoordinate = null;

		// DB 1.1: Verify new counts in DB
		assertEquals(locationCountAfterCreation, locSvc.count());
		assertEquals(pointCountAfterCreation, pointSvc.count());
		assertEquals(relativeLocationCountAfterCreation, rlSvc.count());
		assertEquals(geometryCountAfterCreation, geoSvc.count());
		assertEquals(coordinateCountAfterCreation, coordSvc.count());
		assertEquals(forecastCountAfterCreation, forcSvc.count());
		assertEquals(periodCountAfterCreation, periodSvc.count());

		// 1.1: Verify createdPoint
		assertNotNull(createdPoint);
		assertEquals(typeInitial, createdPoint.getType());
		final int pointId = createdPoint.getId();

		// 1.1: Verify createdPoint's Location
		assertNotNull(createdPoint.getLocation());
		assertEquals(locationId, createdPoint.getLocation().getId());
		assertEquals(locationName, createdPoint.getLocation().getName());
		
		// 1.1: Verify createdPoint's Location's Point
		assertNotNull(createdPoint.getLocation().getPoint());
		assertEquals(pointId, createdPoint.getLocation().getPoint().getId());
		
		// 1.1: Verify createdPoint's Location's Geometry
		assertNotNull(createdPoint.getLocation().getGeometry());
		assertNotNull(createdPoint.getLocation().getGeometry().getCoordinates());
		assertEquals(1, createdPoint.getLocation().getGeometry().getCoordinates().size());
		assertEquals(0, createdPoint.getLocation().getGeometry().getRelativeLocations().size());
		assertNull(createdPoint.getLocation().getGeometry().getPoint());
		assertEquals(0, createdPoint.getLocation().getGeometry().getForecasts().size());
		
		// 1.1: Verify createdPoint's Location's MountainRange and Category
		assertNotNull(createdPoint.getLocation().getMountainRange());
		assertNotNull(createdPoint.getLocation().getCategories());
		assertTrue(createdPoint.getLocation().getCategories().size() > 0);

		// 1.1: Verify createdPoint's Geometry
		assertNotNull(createdPoint.getGeometry());
		assertNull(createdPoint.getGeometry().getLocation());
		assertNull(createdPoint.getGeometry().getRelativeLocations());
		assertNull(createdPoint.getGeometry().getForecasts());
		assertEquals(pointId, createdPoint.getGeometry().getPoint().getId());
		assertEquals(geoTypeInitial, createdPoint.getGeometry().getType());
		final int geoId = createdPoint.getGeometry().getId();

		// 1.1: Verify createdPoint's Geometry's Coordinate
		assertNotNull(createdPoint.getGeometry().getCoordinates());
		assertEquals(1, createdPoint.getGeometry().getCoordinates().size());
		assertEquals(geoId, createdPoint.getGeometry().getCoordinates().get(0).getGeometry().getId());
		assertEquals(pointId, createdPoint.getGeometry().getCoordinates().get(0).getGeometry().getPoint().getId());
		assertEquals(latitudeInitial, createdPoint.getGeometry().getCoordinates().get(0).getLatitude());
		assertEquals(longitudeInitial, createdPoint.getGeometry().getCoordinates().get(0).getLongitude());
		final int geoCoordinateId = createdPoint.getGeometry().getCoordinates().get(0).getId();

		// 1.1: Verify createdPoint's RelativeLocation
		assertNotNull(createdPoint.getRelativeLocation());
		assertEquals(1, createdPoint.getRelativeLocation().getPoints().size());
		assertEquals(pointId, createdPoint.getRelativeLocation().getPoints().get(0).getId());
		assertEquals(rLocTypeInitial, createdPoint.getRelativeLocation().getType());
		final int rLocId = createdPoint.getRelativeLocation().getId();

		// 1.1: Verify createdPoint's RelativeLocation's Geometry
		assertNotNull(createdPoint.getRelativeLocation().getGeometry());
		assertNull(createdPoint.getRelativeLocation().getGeometry().getPoint());
		assertNull(createdPoint.getRelativeLocation().getGeometry().getLocation());
		assertNull(createdPoint.getRelativeLocation().getGeometry().getForecasts());
		assertNotNull(createdPoint.getRelativeLocation().getGeometry().getRelativeLocations());
		assertEquals(1, createdPoint.getRelativeLocation().getGeometry().getRelativeLocations().size());
		assertEquals(rLocId, createdPoint.getRelativeLocation().getGeometry().getRelativeLocations().get(0).getId());
		assertEquals(rLocGeoTypeInitial, createdPoint.getRelativeLocation().getGeometry().getType());
		final int rLocGeoId = createdPoint.getRelativeLocation().getGeometry().getId();

		// 1.1: Verify createdPoint's RelativeLocation's Geometry's Coordinate
		assertNotNull(createdPoint.getRelativeLocation().getGeometry().getCoordinates());
		assertEquals(1, createdPoint.getRelativeLocation().getGeometry().getCoordinates().size());
		assertNull(createdPoint.getRelativeLocation().getGeometry().getCoordinates().get(0).getGeometry().getLocation());
		assertNull(createdPoint.getRelativeLocation().getGeometry().getCoordinates().get(0).getGeometry().getPoint());
		assertNull(createdPoint.getRelativeLocation().getGeometry().getCoordinates().get(0).getGeometry().getForecasts());
		assertNull(createdPoint.getRelativeLocation().getGeometry().getCoordinates().get(0).getGeometry().getPoint());
		assertEquals(rLocGeoId, createdPoint.getRelativeLocation().getGeometry().getCoordinates().get(0).getGeometry().getId());
		assertEquals(rLocLatitudeInitial, createdPoint.getRelativeLocation().getGeometry().getCoordinates().get(0).getLatitude());
		assertEquals(rLocLongitudeInitial, createdPoint.getRelativeLocation().getGeometry().getCoordinates().get(0).getLongitude());
		final int rLocCoordinateId = createdPoint.getRelativeLocation().getGeometry().getCoordinates().get(0).getId();
		
		// 1.1: Verify createdPoint's Forecasts
		assertNotNull(createdPoint.getForecasts());
		assertEquals(numNewForecasts, createdPoint.getForecasts().size());
		
		List<Integer> forecastIds = new ArrayList<>();
		List<Integer> forecastGeoIds = new ArrayList<>();
		List<Integer> forecastCoordinateIds = new ArrayList<>();
		
		for (Forecast forecast : createdPoint.getForecasts()) {
			assertNotNull(forecast);
			assertEquals(pointId, forecast.getPoint().getId());
			assertEquals(forecastTypeInitial, forecast.getType());
			final int forecastId = forecast.getId();
			forecastIds.add(forecastId);
			
			// 1.1: Verify (short) newPoint's Forecasts' Geometry
			assertNotNull(forecast.getGeometry());
			assertEquals(forecastGeoTypeInitial, forecast.getGeometry().getType());
			assertNull(forecast.getGeometry().getLocation());
			assertNull(forecast.getGeometry().getPoint());
			assertNull(forecast.getGeometry().getRelativeLocations());
			final int forecastGeoId = forecast.getGeometry().getId();
			forecastGeoIds.add(forecastGeoId);
			
			// 1.1: Verify (short) newPoint's Forecasts' Geometry's Coordinates
			assertNotNull(forecast.getGeometry().getCoordinates());
			assertEquals(numForecastCoordinates, forecast.getGeometry().getCoordinates().size());
			
			for (Coordinate coordinate : forecast.getGeometry().getCoordinates()) {
				assertNotNull(coordinate);
				assertNotNull(coordinate.getGeometry());
				assertEquals(forecastGeoId, coordinate.getGeometry().getId());
				assertEquals(forecastLatitudeInitial, coordinate.getLatitude());
				assertEquals(forecastLongitudeInitial, coordinate.getLongitude());
				assertNull(coordinate.getGeometry().getLocation());
				assertNull(coordinate.getGeometry().getPoint());
				assertNull(coordinate.getGeometry().getRelativeLocations());
				forecastCoordinateIds.add(coordinate.getId());
			}
		}
		
		assertEquals(numNewForecasts, forecastIds.size());
		assertEquals(numNewForecasts, forecastGeoIds.size());
		assertEquals(numNewForecasts * numForecastCoordinates, forecastCoordinateIds.size());

		
		
		// 1.1: Verify (short) newPoint's Forecasts' Periods

		// 1.1: No longer needed, set returned objects to null

		// DB 1.2: *RETRIEVE* Retrieve newPoint from DB

		// DB 1.2: Verify counts unchanged

		// 1.2: Verify foundPoint's Location

		// 1.2 Verify foundPoint's Geometry

		// 1.2 Verify foundPoint's Geometry's Coordinate

		// 1.2: Verify foundPoint's RelativeLocation

		// 1.2: Verify foundPoint's RelativeLocation's Geometry

		// 1.2: Verify foundPoint's RelativeLocation's Geometry's Coordinate

		// 1.2: Verify foundPoint's Forecasts

		// 1.2: Verify foundPoint's Forecasts' Periods

		// 1.2: No longer needed, set returned objects to null

		// ******
		// STEP 2
		// ******

		// 2.1: Update foundPoint locally

	}

//	@Test
//	@DisplayName("PointService mappings with Location, Geometry, RelativeLocation, Forecast")
//	void test_existing_Point() {
//		// Settings
//		final int pointId = 1;
//
//		// Find point
//		Point point = pointSvc.findById(pointId);
//		assertNotNull(point);
//		assertEquals(pointId, point.getId());
//
//		// Location mappings
//		assertNotNull(point.getLocation());
//		Location location = point.getLocation();
//		assertNotNull(location);
//		final int locationId = location.getId();
//		location = locSvc.findById(locationId);
//		assertNotNull(location);
//		assertNotNull(point.getLocation());
//		assertEquals(location, point.getLocation());
//		assertEquals(point, location.getPoint());
//
//		// Geometry mappings
//		assertNotNull(point.getGeometry());
//		Geometry geometry = point.getGeometry();
//		assertNotNull(geometry);
//		final int geometryId = geometry.getId();
//		geometry = geoSvc.findById(geometryId);
//		assertNotNull(geometry);
//		assertNotNull(geometry.getPoint());
//		assertEquals(geometry, point.getGeometry());
//		assertEquals(point, geometry.getPoint());
//
//		// RelativeLocation mappings
//		assertNotNull(point.getRelativeLocation());
//		RelativeLocation relativeLocation = point.getRelativeLocation();
//		assertNotNull(relativeLocation);
//		final int relativeLocationId = relativeLocation.getId();
//		relativeLocation = rlSvc.findById(relativeLocationId);
//		assertNotNull(relativeLocation);
////		assertNotNull(relativeLocation.getPoints());
////		assertTrue(relativeLocation.getPoints().size() > 0);
////		assertTrue(relativeLocation.getPoints().contains(point));
//		assertEquals(relativeLocation, point.getRelativeLocation());
//
//		// Forecast mappings
//		assertNotNull(point.getForecasts());
//		List<Forecast> forecasts = point.getForecasts();
//		assertNotNull(forecasts);
//		assertTrue(forecasts.size() > 0);
//		Forecast forecast = forecasts.get(0);
//		assertNotNull(forecast);
//		final int forecastId = forecast.getId();
//		forecast = forcSvc.findById(forecastId);
//		assertNotNull(forecast);
////		assertNotNull(forecast.getPoints());
////		List<Point> points = forecast.getPoints();
////		assertNotNull(points);
////		assertTrue(points.contains(point));
//	}

//	@Test
//	@DisplayName("PointService Update with Location, Geometry, RelativeLocation, Forecast")
//	void test_CRUD_Point() {
//		// Settings
//		final int pointId = 1;
//		final String newcwa = "New cwa";
//		final String updatedCwa = "Updated";
//		assertNotNull(pointSvc.index());
//		assertTrue(pointSvc.index().size() > 0);
//		final int numPoints = pointSvc.index().size();
//		final String newLocName = "New Name";
//		final String updatedLocName = "Updated";
//
//		// Find Point and verify
//		Point point = pointSvc.findById(pointId);
//		assertNotNull(point);
//		assertEquals(pointId, point.getId());
//		assertNotNull(point.getLocation());
//		final String originalCwa = point.getCwa();
//
//		// Get Point's Location and verify
//		Location location = point.getLocation();
//		assertNotNull(location);
//		final int locId = location.getId();
//		location = locSvc.findById(locId);
//		assertNotNull(location);
//		assertEquals(locId, location.getId());
//		assertEquals(locId, point.getLocation().getId());
//		assertEquals(location, point.getLocation());
//		assertNotNull(location.getPoint());
//		assertEquals(pointId, location.getPoint().getId());
//		assertEquals(point, location.getPoint());
//		assertNotNull(location.getName());
//		final String locName = location.getName();
//
//		// Update Point cwa on DB
//		point.setCwa(updatedCwa);
//		Point updatedPoint = pointSvc.update(point);
//		assertNotNull(updatedPoint);
//		assertEquals(pointId, updatedPoint.getId());
//		assertEquals(numPoints, pointSvc.index().size()); // nothing new created
//		point = pointSvc.findById(pointId);
//		assertNotNull(point);
//		assertEquals(updatedCwa, point.getCwa());
//		assertEquals(point, updatedPoint);
//
//		// Verify Location
//		location = locSvc.findById(locId);
//		assertEquals(point, location.getPoint());
//		assertEquals(location, point.getLocation());
//		assertEquals(updatedCwa, location.getPoint().getCwa());
//
//		// Verify Geometry
//		Geometry geometry = point.getGeometry();
//		assertNotNull(geometry);
//		assertEquals(point, geometry.getPoint());
//		assertEquals(geometry, point.getGeometry());
//		final int geometryId = geometry.getId();
//		geometry = geoSvc.findById(geometryId);
//		assertNotNull(geometry);
//		assertEquals(geometry, point.getGeometry());
//		assertEquals(point, geometry.getPoint());
//		assertEquals(updatedCwa, geometry.getPoint().getCwa());
//
//		// Verify RelativeLocation
//		RelativeLocation relativeLocation = point.getRelativeLocation();
//		assertNotNull(relativeLocation);
////		assertTrue(relativeLocation.getPoints().contains(point));
//		final int relativeLocationId = relativeLocation.getId();
//		relativeLocation = rlSvc.findById(relativeLocationId);
//		assertNotNull(relativeLocation);
////		assertTrue(relativeLocation.getPoints().contains(point));
//		assertEquals(relativeLocation, point.getRelativeLocation());
//		boolean rlHasPointWithCwa = false;
//		for (Point rlPoint : relativeLocation.getPoints()) {
//			if (rlPoint.getCwa().equals(updatedCwa)) {
//				rlHasPointWithCwa = true;
//			}
//		}
//		assertTrue(rlHasPointWithCwa);

//		// Verify Forecast
//		assertNotNull(point.getForecasts());
//		assertTrue(point.getForecasts().size() > 0);
//		Forecast forecast = point.getForecasts().get(0);
//		assertNotNull(forecast);
//		assertTrue(point.getForecasts().contains(forecast));
//		final int forecastId = forecast.getId();
//		forecast = forcSvc.findById(forecastId);
//		assertNotNull(forecast);
//		assertTrue(point.getForecasts().contains(forecast));
//		assertTrue(forecast.getPoints().contains(point));
//		boolean forecastHasPointWithCwa = false;
//		for (Point fcPoint : forecast.getPoints()) {
//			if (fcPoint.getCwa().equals(updatedCwa)) {
//				forecastHasPointWithCwa = true;
//			}
//		}
//		assertTrue(forecastHasPointWithCwa);

//		// Revert Point cwa on DB
//		point.setCwa(originalCwa);
//		updatedPoint = pointSvc.update(point);
//		assertNotNull(updatedPoint);
//		assertEquals(pointId, updatedPoint.getId());
//		assertEquals(numPoints, pointSvc.index().size()); // nothing new created
//		point = pointSvc.findById(pointId);
//		assertNotNull(point);
//		assertEquals(originalCwa, point.getCwa());
//		assertEquals(point, updatedPoint);
//
//		// Verify Location
//		location = locSvc.findById(locId);
//		assertEquals(point, location.getPoint());
//		assertEquals(location, point.getLocation());
//		assertEquals(originalCwa, location.getPoint().getCwa());
//
//		// Verify Geometry
//		geometry = point.getGeometry();
//		assertNotNull(geometry);
//		assertEquals(point, geometry.getPoint());
//		assertEquals(geometry, point.getGeometry());
//		geometry = geoSvc.findById(geometryId);
//		assertNotNull(geometry);
//		assertEquals(geometry, point.getGeometry());
//		assertEquals(point, geometry.getPoint());
//		assertEquals(originalCwa, geometry.getPoint().getCwa());
//
//		// Verify RelativeLocation
//		relativeLocation = point.getRelativeLocation();
//		assertNotNull(relativeLocation);
////		assertTrue(relativeLocation.getPoints().contains(point));
//		relativeLocation = rlSvc.findById(relativeLocationId);
//		assertNotNull(relativeLocation);
////		assertTrue(relativeLocation.getPoints().contains(point));
//		assertEquals(relativeLocation, point.getRelativeLocation());
//		rlHasPointWithCwa = false;
//		for (Point rlPoint : relativeLocation.getPoints()) {
//			if (rlPoint.getCwa().equals(originalCwa)) {
//				rlHasPointWithCwa = true;
//			}
//		}
//		assertTrue(rlHasPointWithCwa);

	// Verify Forecast
//		assertNotNull(point.getForecasts());
//		assertTrue(point.getForecasts().size() > 0);
//		forecast = point.getForecasts().get(0);
//		assertNotNull(forecast);
//		assertTrue(point.getForecasts().contains(forecast));
//		forecast = forcSvc.findById(forecastId);
//		assertNotNull(forecast);
//		assertTrue(point.getForecasts().contains(forecast));
//		assertTrue(forecast.getPoints().contains(point));
//		forecastHasPointWithCwa = false;
//		for (Point fcPoint : forecast.getPoints()) {
//			if (fcPoint.getCwa().equals(originalCwa)) {
//				forecastHasPointWithCwa = true;
//			}
//		}
//		assertTrue(forecastHasPointWithCwa);
//
//	}
//
//	@Test
//	@DisplayName("PointService CR_D with Location, Geometry, Coordinate, RelativeLocation, Forecast ")
//	void test_create() {
//		// Settings
//		final String newCwa = "New CWA";
//		final String locName = "Location Name";
//		final int categoryId = 1;
//		final int rangeId = 5;
//		final double locCoordLatitude = 11.11;
//		final double locCoordLongitude = -22.22;
//		final double pointCoordLatitude = 44.44;
//		final double pointCoordLongitude = -55.55;
//		final double rlCoordLatitude = 77.77;
//		final double rlCoordLongitude = -88.88;
//		final double fcCoordLatitude = 98.76;
//		final double fcCoordLongitude = -54.32;
//		final int locCountInit = locSvc.index().size();
//		final int rangeCountInit = mrSvc.index().size();
//		final int catCountInit = catSvc.index().size();
//		final int pointCountInit = pointSvc.index().size();
//		final int rlCountInit = rlSvc.index().size();
//		final int geoCountInit = geoSvc.index().size();
//		final int coordCountInit = coordSvc.index().size();
//		final int forcCountInit = forcSvc.index().size();
//
//		// Create Location
//		Location location = new Location();
//		location.setName(locName);
//
//		// Create Coordinate and add to location
//		Coordinate locCoord = new Coordinate();
//		locCoord.setLatitude(locCoordLatitude);
//		locCoord.setLongitude(locCoordLongitude);
//		location.setCoordinate(locCoord);
//		
//		// Find Category and add to Location
//		Category locCat = catSvc.findById(categoryId);
//		assertNotNull(locCat);
//		location.addCategory(locCat);
//
//		// Find MountainRange and add to Location
//		MountainRange range = mrSvc.findById(rangeId);
//		assertNotNull(range);
//		location.setMountainRange(range);
//		
//		// CREATE #1: Create new Location in DB
//		Location newLocation = locSvc.create(location);
//		assertNotNull(newLocation);
//		final int newLocationId = newLocation.getId();
//		newLocation = locSvc.findById(newLocationId);
//		assertNotNull(newLocation);
//		
//		// Verify Location has Coordinate
//		assertNotNull(newLocation.getCoordinate());
//		assertEquals(locCoord, newLocation.getCoordinate());
//		
//		// Verify Location has Category
//		assertNotNull(newLocation.getCategories());
//		assertTrue(newLocation.getCategories().size() > 0);
//		assertTrue(newLocation.getCategories().contains(locCat));
//		
//		// Verify Location has MountainRange
//		assertNotNull(newLocation.getMountainRange());
//		assertEquals(range, newLocation.getMountainRange());
//
//		// Create Point and set Location and vice-versa
//		Point point = new Point();
//		point.setCwa(newCwa);
//		point.setLocation(location);
//		location.setPoint(point);
//
//		// Create Geometry for Point
//		Geometry pointGeometry = new Geometry();
//
//		// Create Coordinate and add to Geometry for Point and vice-versa
//		Coordinate geoCoordinate = new Coordinate();
//		geoCoordinate.setLatitude(pointCoordLatitude);
//		geoCoordinate.setLongitude(pointCoordLongitude);
//		pointGeometry.addCoordinate(geoCoordinate);
//		geoCoordinate.setGeometry(pointGeometry);
//
//		// Set Geometry to Point and vice-versa
//		point.setGeometry(pointGeometry);
//		pointGeometry.setPoint(point);
//
//		// Create RelativeLocation
//		RelativeLocation relativeLocation = new RelativeLocation();
//
//		// Create geometry for RelativeLocation
//		Geometry rlGeometry = new Geometry();
//		
//		// Create Coordinate and add to Geometry for RelativeLocation and vice-versa
//		Coordinate rlCoordinate = new Coordinate();
//		rlCoordinate.setLatitude(rlCoordLatitude);
//		rlCoordinate.setLongitude(rlCoordLongitude);
//		rlGeometry.addCoordinate(rlCoordinate);
//		rlCoordinate.setGeometry(rlGeometry);
//		
//		// Set Geometry to RelativeLocation and vice-versa
//		relativeLocation.setGeometry(rlGeometry);
//		rlGeometry.setPoint(point);
//		
//		// Set RelativeLocation to Point and add to relativeLocation
//		point.setRelativeLocation(relativeLocation);
////		relativeLocation.addPoint(point);
//		
//		// Create Forecast
//		Forecast forecast = new Forecast();
//		
//		// Create Geometry for Forecast
//		Geometry fcGeometry = new Geometry();
//		
//		// Create Coordinate and add to Geometry for Forecast and vice-versa
//		Coordinate fcCoordinate = new Coordinate();
//		fcCoordinate.setLatitude(fcCoordLatitude);
//		fcCoordinate.setLongitude(fcCoordLongitude);
//		fcGeometry.addCoordinate(fcCoordinate);
//		fcCoordinate.setGeometry(fcGeometry);
//		
//		// Set Geomery to Forecast and add to geometry
//		forecast.setGeometry(fcGeometry);
////		fcGeometry.addForecast(forecast);
//		
//		// Add Forecast to Point and vice-versa
////		forecast.addPoint(point);
//		point.addForecast(forecast);
//		
//		// Persist Point to DB
//		Point newPoint = pointSvc.create(point);
//		assertNotNull(newPoint);
//
//		// Verify Location in DB
//
//		// Verify Geometry in DB
//
//		// Verify Coordinate in DB
//
//		// Verify RelativeLocation in DB
//
//		// Verify connections
//	}

}
