package com.niceweatherrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.niceweatherjpa.entities.Coordinate;
import com.niceweatherjpa.entities.Geometry;

@SpringBootTest
class CoordinateServiceTest {
	
	@Autowired
	private GeometryServiceImpl geoSvc;

	@Autowired
	private CoordinateServiceImpl coordSvc;

	@BeforeEach
	void setUp() throws Exception {
		assertNotNull(geoSvc);
		assertNotNull(coordSvc);
	}

	@AfterEach
	void tearDown() throws Exception {
		geoSvc = null;
		coordSvc = null;
	}
	
	@Test
	@DisplayName("CoordinateService index() and count()")
	void test_index() {
		assertNotNull(coordSvc.index());
		assertTrue(coordSvc.count() > 0);
	}

	@Test
	@DisplayName("CoordinateService update() with Geometry")
	void test_update() {
		// Settings
		final int coordinateId = 1;
		final double updatedLatitude = 111.11;
		final double updatedLongitude = -222.22;
		final long coordinateCountInitial = coordSvc.count();
		
		// Find in DB and set updates
		Coordinate coordinate = coordSvc.findById(coordinateId);
		assertNotNull(coordinate);
		final double initialLatitude = coordinate.getLatitude();
		final double initialLongitude = coordinate.getLongitude();
		final Geometry initialGeometry = coordinate.getGeometry();
		assertNotNull(initialGeometry);
		
		// Update
		coordinate.setLatitude(updatedLatitude);
		coordinate.setLongitude(updatedLongitude);
		Coordinate updatedCoordinate = coordSvc.update(coordinate);
		coordinate = null;
		
		// Verify
		assertEquals(updatedLatitude, updatedCoordinate.getLatitude());
		assertEquals(updatedLongitude, updatedCoordinate.getLongitude());
		assertNotNull(updatedCoordinate.getGeometry());
		assertEquals(initialGeometry, updatedCoordinate.getGeometry());
		updatedCoordinate = null;
		
		// Find in DB
		Coordinate foundCoordinate = coordSvc.findById(coordinateId);
		
		// Verify
		assertNotNull(foundCoordinate);
		assertEquals(updatedLatitude, foundCoordinate.getLatitude());
		assertEquals(updatedLongitude, foundCoordinate.getLongitude());
		assertNotNull(foundCoordinate.getGeometry());
		assertEquals(initialGeometry, foundCoordinate.getGeometry());
		
		// Revert
		foundCoordinate.setLatitude(initialLatitude);
		foundCoordinate.setLongitude(initialLongitude);
		Coordinate revertedCoordinate = coordSvc.update(foundCoordinate);
		foundCoordinate = null;
		
		// Verify
		assertNotNull(revertedCoordinate);
		assertEquals(initialLatitude, revertedCoordinate.getLatitude());
		assertEquals(initialLongitude, revertedCoordinate.getLongitude());
		assertNotNull(revertedCoordinate.getGeometry());
		assertEquals(initialGeometry, revertedCoordinate.getGeometry());
		revertedCoordinate = null;
		
		// Find in DB
		Coordinate finalCoordinate = coordSvc.findById(coordinateId);
		
		// Verify
		assertNotNull(finalCoordinate);
		assertEquals(initialLatitude, finalCoordinate.getLatitude());
		assertEquals(initialLongitude, finalCoordinate.getLongitude());
		assertNotNull(finalCoordinate.getGeometry());
		assertEquals(initialGeometry, finalCoordinate.getGeometry());
		
		// Verify index().size remains the same
		assertEquals(coordinateCountInitial, coordSvc.count());
	}
	

}
