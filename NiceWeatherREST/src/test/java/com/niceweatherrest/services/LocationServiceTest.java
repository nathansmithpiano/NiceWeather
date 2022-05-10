package com.niceweatherrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.niceweatherjpa.entities.Category;
import com.niceweatherjpa.entities.Coordinate;
import com.niceweatherjpa.entities.Geometry;
import com.niceweatherjpa.entities.Location;
import com.niceweatherjpa.entities.MountainRange;

@SpringBootTest
class LocationServiceTest {

	@Autowired
	private LocationServiceImpl locSvc;

	@Autowired
	private MountainRangeServiceImpl mrSvc;

	@Autowired
	private CategoryServiceImpl catSvc;

	@Autowired
	private CoordinateServiceImpl coordSvc;

	@Autowired
	private GeometryServiceImpl geoSvc;

//	@Autowired
//	private PointServiceImpl pointSvc;

	@BeforeEach
	void setUp() throws Exception {
		assertNotNull(locSvc);
		assertNotNull(mrSvc);
		assertNotNull(catSvc);
		assertNotNull(coordSvc);
		assertNotNull(geoSvc);
//		assertNotNull(pointSvc);
	}

	@AfterEach
	void tearDown() throws Exception {
		locSvc = null;
		mrSvc = null;
		catSvc = null;
		coordSvc = null;
		geoSvc = null;
//		pointSvc = null;
	}

	@Test
	@DisplayName("LocationService index()")
	void test_index() {
		assertNotNull(locSvc.index());
		assertTrue(locSvc.index().size() > 0);
	}

	@Test
	@DisplayName("LocationService update()")
	void test_update() {
		// Change and revert name

		// Settings
		final int idInit = 1;
		final String updatedName = "Updated";
		final int locationCount = locSvc.index().size();

		// Find Location
		Location location = locSvc.findById(idInit);
		assertNotNull(location);
		final String initialName = location.getName();

		// Update locally
		location.setName(updatedName);
		// No changes yet
		assertEquals(initialName, locSvc.findById(idInit).getName());

		// Update on DB
		location = locSvc.update(location);
		assertNotNull(location);
		
		// Verify
		assertEquals(idInit, location.getId());
		assertEquals(updatedName, location.getName());
		assertEquals(updatedName, locSvc.findById(idInit).getName()); // changes made

		// Revert locally
		location.setName(initialName);
		// No changes yet
		assertEquals(initialName, location.getName());

		// Revert on DB
		location = locSvc.update(location);
		assertNotNull(location);
		
		// Verify
		assertEquals(idInit, location.getId());
		assertEquals(initialName, location.getName());
		assertEquals(initialName, locSvc.findById(idInit).getName()); // changed

		// Verify nothing new created
		assertEquals(locationCount, locSvc.index().size());
	}

	@Test
	@DisplayName("LocationService CR_D with Coordinate, Category, MountainRange")
	void test_CR_D() {
		// Create location with new Geometry, new Coordinate, existing MountainRange, 2
		// existing Categories
		// Remove new entities from DB

		// Settings
		String newName = "New Name";
		final double latitude = 12.34;
		final double longitude = -98.76;
		final int mtnRangeId = 9;
		final int cat1Id = 1;
		final int cat2Id = 5;
		final String geometryType = "Temp Type";
		final int locationCount = locSvc.index().size();
		final int categoryCount = catSvc.index().size();
		final int rangeCount = mrSvc.index().size();
		final int geometryCount = geoSvc.index().size();
		final int coordinateCount = coordSvc.index().size();

		// Create new Location locally
		Location loc = new Location();
		loc.setName(newName);

		// Create new Coordinate locally (non-null)
		Coordinate coordinate = new Coordinate();
		coordinate.setLatitude(latitude);
		coordinate.setLongitude(longitude);

		// Create new Geometry locally (non-null)
		Geometry geometry = new Geometry();
		geometry.setType(geometryType);
		geometry.addCoordinate(coordinate);

		// Verify Coordinate and Geometry locally
		assertEquals(geometry, coordinate.getGeometry());
		assertTrue(geometry.getCoordinates().contains(coordinate));

		// Set to loc
		loc.setGeometry(geometry);

		// Find 2 Categories from DB
		Category cat1 = catSvc.findById(cat1Id);
		assertNotNull(cat1);
		assertEquals(cat1Id, cat1.getId());
		Category cat2 = catSvc.findById(cat2Id);
		assertNotNull(cat2);
		assertEquals(cat2Id, cat2.getId());
		// add both to loc
		loc.addCategory(cat1);
		loc.addCategory(cat2);

		// Find MountainRange from DB
		MountainRange range = mrSvc.findById(mtnRangeId);
		// Set to loc
		loc.setMountainRange(range);

		// Persist all to DB
		Location newLoc = locSvc.create(loc);
		assertNotNull(newLoc);
		final int newId = newLoc.getId();
		newLoc = locSvc.findById(newId);
		assertNotNull(newLoc);
		assertTrue(newLoc.getId() > 0);

		// get new Geometry from DB
		assertNotNull(newLoc.getGeometry());
		final int newGeometryId = newLoc.getGeometry().getId();
		Geometry newGeometry = geoSvc.findById(newGeometryId);
		assertNotNull(newGeometry);

		// Get new Coordinate from DB
		assertNotNull(newGeometry.getCoordinates());
		assertTrue(newGeometry.getCoordinates().size() > 0);
		assertTrue(newGeometry.getCoordinates().size() == 1);
		final int newCoordinateId = newGeometry.getCoordinates().iterator().next().getId();
		Coordinate newCoordinate = coordSvc.findById(newCoordinateId);
		assertNotNull(newCoordinate);

		// Verify Geometry and Coordinate
		assertEquals(newGeometry, newCoordinate.getGeometry());
		assertTrue(newGeometry.getCoordinates().contains(newCoordinate));
		assertEquals(latitude, newCoordinate.getLatitude());
		assertEquals(longitude, newCoordinate.getLongitude());

		// Verify MountainRange
		assertNotNull(mrSvc.findById(mtnRangeId));
		assertNotNull(newLoc.getMountainRange());
		assertEquals(newLoc.getMountainRange(), mrSvc.findById(mtnRangeId));

		// Verify Categories
		assertNotNull(newLoc.getCategories());
		assertTrue(newLoc.getCategories().size() == 2);
		assertTrue(newLoc.getCategories().contains(cat1));
		assertTrue(newLoc.getCategories().contains(cat2));

		// Delete from DB
		assertTrue(locSvc.deleteById(newId));
		assertNull(locSvc.findById(newId));

		// Verify Coordinate deleted
		assertNull(coordSvc.findById(newCoordinateId));

		// Verify MountainRange NOT deleted
		assertNotNull(mrSvc.findById(mtnRangeId));
		assertFalse(mrSvc.findById(mtnRangeId).getLocations().contains(newLoc));

		// Verify Categories NOT deleted
		assertNotNull(catSvc.findById(cat1Id));
		assertFalse(catSvc.findById(cat1Id).getLocations().contains(newLoc));
		assertNotNull(catSvc.findById(cat2Id));
		assertFalse(catSvc.findById(cat2Id).getLocations().contains(newLoc));

		// Verify counts match for all tables
		assertEquals(locationCount, locSvc.index().size());
		assertEquals(categoryCount, catSvc.index().size());
		assertEquals(rangeCount, mrSvc.index().size());
		assertEquals(geometryCount, geoSvc.index().size());
		assertEquals(coordinateCount, coordSvc.index().size());
	}

//	@Test
//	@DisplayName("LocationService Point")
//	void test_point() {
//		// Settings
//		final int locId = 1;
//
//		// Find Location
//		Location location = locSvc.findById(locId);
//		assertNotNull(locId);
//
////		// Get Point for Location
////		Point point = location.getPoint();
////		assertNotNull(point);
////		final int pointId = point.getId();
////		point = null;
////		point = pointSvc.findById(pointId);
////		assertNotNull(point);
////		assertEquals(point, location.getPoint());
////		assertEquals(location, point.getLocation());
//
//	}

}
