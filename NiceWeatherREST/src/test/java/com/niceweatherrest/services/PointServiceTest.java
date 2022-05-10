//package com.niceweatherrest.services;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class PointServiceTest {
//
//	@Autowired
//	private PointServiceImpl pointSvc;
//
//	@Autowired
//	private LocationServiceImpl locSvc;
//
//	@Autowired
//	private CoordinateServiceImpl coordSvc;
//
//	@Autowired
//	private CategoryServiceImpl catSvc;
//
//	@Autowired
//	private MountainRangeService mrSvc;
//
//	@Autowired
//	private GeometryServiceImpl geoSvc;
//
//	@Autowired
//	private RelativeLocationServiceImpl rlSvc;
//
//	@Autowired
//	private ForecastServiceImpl forcSvc;
//
//	@BeforeEach
//	void setUp() throws Exception {
//		assertNotNull(pointSvc);
//		assertNotNull(locSvc);
//		assertNotNull(coordSvc);
//		assertNotNull(catSvc);
//		assertNotNull(mrSvc);
//		assertNotNull(geoSvc);
//		assertNotNull(rlSvc);
//		assertNotNull(forcSvc);
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//		pointSvc = null;
//		locSvc = null;
//		coordSvc = null;
//		catSvc = null;
//		mrSvc = null;
//		geoSvc = null;
//		rlSvc = null;
//		forcSvc = null;
//	}
//
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
//
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
////		boolean rlHasPointWithCwa = false;
////		for (Point rlPoint : relativeLocation.getPoints()) {
////			if (rlPoint.getCwa().equals(updatedCwa)) {
////				rlHasPointWithCwa = true;
////			}
////		}
////		assertTrue(rlHasPointWithCwa);
//
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
////		assertTrue(forecast.getPoints().contains(point));
////		boolean forecastHasPointWithCwa = false;
////		for (Point fcPoint : forecast.getPoints()) {
////			if (fcPoint.getCwa().equals(updatedCwa)) {
////				forecastHasPointWithCwa = true;
////			}
////		}
////		assertTrue(forecastHasPointWithCwa);
//
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
////		rlHasPointWithCwa = false;
////		for (Point rlPoint : relativeLocation.getPoints()) {
////			if (rlPoint.getCwa().equals(originalCwa)) {
////				rlHasPointWithCwa = true;
////			}
////		}
////		assertTrue(rlHasPointWithCwa);
//
//		// Verify Forecast
//		assertNotNull(point.getForecasts());
//		assertTrue(point.getForecasts().size() > 0);
//		forecast = point.getForecasts().get(0);
//		assertNotNull(forecast);
//		assertTrue(point.getForecasts().contains(forecast));
//		forecast = forcSvc.findById(forecastId);
//		assertNotNull(forecast);
//		assertTrue(point.getForecasts().contains(forecast));
////		assertTrue(forecast.getPoints().contains(point));
////		forecastHasPointWithCwa = false;
////		for (Point fcPoint : forecast.getPoints()) {
////			if (fcPoint.getCwa().equals(originalCwa)) {
////				forecastHasPointWithCwa = true;
////			}
////		}
////		assertTrue(forecastHasPointWithCwa);
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
//
//}
