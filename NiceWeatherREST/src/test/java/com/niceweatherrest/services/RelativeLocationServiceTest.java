package com.niceweatherrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.niceweatherjpa.entities.Coordinate;
import com.niceweatherjpa.entities.Geometry;
import com.niceweatherjpa.entities.RelativeLocation;

@SpringBootTest
class RelativeLocationServiceTest {
	
	@Autowired
	private RelativeLocationServiceImpl rlSvc;
	
	@Autowired
	private CoordinateServiceImpl coordSvc;
	
	@Autowired
	private GeometryServiceImpl geoSvc;
	
	@BeforeEach
	void setUp() throws Exception {
		assertNotNull(rlSvc);
		assertNotNull(coordSvc);
		assertNotNull(geoSvc);
	}

	@AfterEach
	void tearDown() throws Exception {
		rlSvc = null;
		coordSvc = null;
		geoSvc = null;
	}
	
	@Test
	@DisplayName("RelativeLocationService index() and count()")
	void test_index() {
		assertNotNull(rlSvc.index());
		assertTrue(rlSvc.count() > 0);
	}
	
	@Test
	@DisplayName("RelativeLocationService CRUD with 1 Geometry and 1 Coordinate")
	void test_CRUD_with_Geometry_Coordinates() {
		// 1: Create new RelativeLocation with Geometry and 1 Coordinate and verify
		// 2: Update RelativeLocation's city and verify
		// 3: Update RelativeLocation's Geometry's Coordinate and verify
		// 4: Delete all and verify
		
		// Settings
		final String cityInitial = "New City";
		final String cityUpdated = "Updated City";
		final String geometryTypeUpdated = "Updated Type";
		final int numGeometryCoordinates = 1;
		final double latitudeInitial = 111.11;
		final double longitudeInitial = -222.22;
		final double latitudeUpdated = 333.33;
		final double longitudeUpdated = -444.44;
		
		// Used for verification
		final Long relativeLocationCountInitial = rlSvc.count();
		final Long geometryCountInitial = geoSvc.count();
		final Long coordinateCountInitial = coordSvc.count();
		
		// ******
		// STEP 1
		// ******
		
		// 1.1: Create RelativeLocation locally
		RelativeLocation rLocInitial = new RelativeLocation();
		rLocInitial.setCity(cityInitial);
		
		// 1.1: Create Geometry locally
		Geometry geometryInitial = new Geometry();
		
		// 1.1: Create Coordinate locally and add to Geometry locally
		Coordinate coordinateInitial = new Coordinate();
		coordinateInitial.setLatitude(latitudeInitial);
		coordinateInitial.setLongitude(longitudeInitial);
		geometryInitial.addCoordinate(coordinateInitial);
		
		// 1.1: set Geometry to RelativeLocation locally
		rLocInitial.setGeometry(geometryInitial);
		
		// 1.1: Verify all locally
		assertNotNull(rLocInitial);
		assertNotNull(geometryInitial);
		assertNotNull(rLocInitial.getGeometry());
		assertEquals(geometryInitial, rLocInitial.getGeometry());
		assertNotNull(coordinateInitial);
		assertNotNull(geometryInitial.getCoordinates());
		assertTrue(geometryInitial.getCoordinates().size() > 0);
		assertEquals(numGeometryCoordinates, geometryInitial.getCoordinates().size());
		
		// DB 1.1: *CREATE* Create RelativeLocation, Geometry, and Coordinate in DB
		RelativeLocation newRLoc = rlSvc.create(rLocInitial);
		
		// 1.1: No longer needed, set initial objects to null
		rLocInitial = null;
		geometryInitial = null;
		coordinateInitial = null;
		
		// DB 1.1: Verify new counts in DB
		final long relativeLocationCountNew = rlSvc.count();
		final long geometryCountNew = geoSvc.count();
		final long coordinateCountNew = coordSvc.count();
		assertEquals(relativeLocationCountInitial + 1, relativeLocationCountNew);
		assertEquals(geometryCountInitial + 1, geometryCountNew);
		assertEquals(coordinateCountInitial + numGeometryCoordinates, coordinateCountNew);
		
		// 1.1: Verify newRLoc
		assertNotNull(newRLoc);
		assertEquals(cityInitial, newRLoc.getCity());
		final int newId = newRLoc.getId();
		
		// 1.1: Verify newRLoc's Geometry and Coordinate
		assertNotNull(newRLoc.getGeometry());
		Geometry newRLocGeo = newRLoc.getGeometry();
		assertNotNull(newRLocGeo);
		final int newGeoId = newRLocGeo.getId();
		
		// 1.1: Verify newRLocGeo's Coordinate
		assertEquals(numGeometryCoordinates, newRLocGeo.getCoordinates().size());
		Coordinate newRLocGeoCoord = newRLocGeo.getCoordinates().iterator().next();
		assertNotNull(newRLocGeoCoord);
		final int newCoordId = newRLocGeoCoord.getId();
		assertEquals(latitudeInitial, newRLocGeoCoord.getLatitude());
		assertEquals(longitudeInitial, newRLocGeoCoord.getLongitude());
		
		// 1.1: No longer needed, set returned objects to null
		newRLoc = null;
		newRLocGeo = null;
		newRLocGeoCoord = null;
		
		// DB 1.2: *RETRIEVE* Find RelativeLocation in DB
		RelativeLocation foundRLoc = rlSvc.findById(newId);
		
		// DB 1.2: Verify counts unchanged
		assertEquals(relativeLocationCountNew, rlSvc.count());
		assertEquals(geometryCountNew, geoSvc.count());
		assertEquals(coordinateCountNew, coordSvc.count());
		
		// 1.2: Verify foundRLoc
		assertNotNull(foundRLoc);
		assertEquals(newId, foundRLoc.getId());
		assertEquals(cityInitial, foundRLoc.getCity());
		
		// 1.2: Verify foundRLoc's Geometry and Coordinate
		assertNotNull(foundRLoc.getGeometry());
		Geometry foundRLocGeo = foundRLoc.getGeometry();
		assertNotNull(foundRLocGeo);
		
		// 1.2: Verify foundRLocGeo Coordinate
		assertEquals(numGeometryCoordinates, foundRLocGeo.getCoordinates().size());
		Coordinate foundRLocGeoCoord = foundRLocGeo.getCoordinates().iterator().next();
		assertNotNull(foundRLocGeoCoord);
		assertEquals(latitudeInitial, foundRLocGeoCoord.getLatitude());
		assertEquals(longitudeInitial, foundRLocGeoCoord.getLongitude());
		
		// 1.2: No longer needed, set new Geometry and Coordinate
		foundRLocGeo = null;
		foundRLocGeoCoord = null;
		
		// ******
		// STEP 2
		// ******
		
		// 2.1: Update RelativeLocation locally
		foundRLoc.setCity(cityUpdated);
		
		// DB 2.1: *UPDATE* Update RelativeLocation in DB
		RelativeLocation updatedRLoc = rlSvc.update(foundRLoc);
		
		// 2.1: No longer needed, set foundRLoc to null
		foundRLoc = null;
		
		// DB 2.1: Verify counts unchanged
		assertEquals(relativeLocationCountNew, rlSvc.count());
		assertEquals(geometryCountNew, geoSvc.count());
		assertEquals(coordinateCountNew, coordSvc.count());
		
		// 2.1: Verify updatedRLoc
		assertNotNull(updatedRLoc);
		assertEquals(newId, updatedRLoc.getId());
		assertEquals(cityUpdated, updatedRLoc.getCity());
		
		// 2.1: Verify foundRLoc's Geometry and Coordinate
		assertNotNull(updatedRLoc.getGeometry());
		Geometry updatedRLocGeo = updatedRLoc.getGeometry();
		assertNotNull(updatedRLocGeo);
		
		// 2.1: Verify foundRLocGeo Coordinate
		assertEquals(numGeometryCoordinates, updatedRLocGeo.getCoordinates().size());
		Coordinate updatedRLocGeoCoord = updatedRLocGeo.getCoordinates().iterator().next();
		assertNotNull(updatedRLocGeoCoord);
		assertEquals(latitudeInitial, updatedRLocGeoCoord.getLatitude());
		assertEquals(longitudeInitial, updatedRLocGeoCoord.getLongitude());
		
		// 2.1: No longer needed, set objects to null
		updatedRLoc = null;
		updatedRLocGeo = null;
		updatedRLocGeoCoord = null;
		
		// DB 2.2: *RETRIEVE* Find RelativeLocation in DB
		RelativeLocation foundUpdatedRLoc = rlSvc.findById(newId);
		
		// DB 2.1: Verify counts unchanged
		assertEquals(relativeLocationCountNew, rlSvc.count());
		assertEquals(geometryCountNew, geoSvc.count());
		assertEquals(coordinateCountNew, coordSvc.count());
		
		// 2.1: Verify foundUpdatedRLoc
		assertNotNull(foundUpdatedRLoc);
		assertEquals(newId, foundUpdatedRLoc.getId());
		assertEquals(cityUpdated, foundUpdatedRLoc.getCity());
		
		// 2.1: Verify foundUpdatedRLoc's Geometry and Coordinate
		assertNotNull(foundUpdatedRLoc.getGeometry());
		Geometry foundUpdatedRLocGeo = foundUpdatedRLoc.getGeometry();
		assertNotNull(foundUpdatedRLocGeo);
		
		// 2.1: Verify foundRLocGeo Coordinate
		assertEquals(numGeometryCoordinates, foundUpdatedRLocGeo.getCoordinates().size());
		Coordinate foundUpdatedRLocGeoCoord = foundUpdatedRLocGeo.getCoordinates().iterator().next();
		assertNotNull(foundUpdatedRLocGeoCoord);
		assertEquals(latitudeInitial, foundUpdatedRLocGeoCoord.getLatitude());
		assertEquals(longitudeInitial, foundUpdatedRLocGeoCoord.getLongitude());
		
		// 2.1: No longer needed, set objects to null
		foundUpdatedRLocGeo = null;
		foundUpdatedRLocGeoCoord = null;
		
		// ******
		// STEP 3
		// ******
		
		// 3.1: Update RelativeLocation's Geometry and Coordinate locally
		foundUpdatedRLoc.getGeometry().setType(geometryTypeUpdated);
		foundUpdatedRLoc.getGeometry().getCoordinates().iterator().next().setLatitude(latitudeUpdated);
		foundUpdatedRLoc.getGeometry().getCoordinates().iterator().next().setLongitude(longitudeUpdated);
		
		// DB 3.1: *UPDATE* update foundUpdatedRLoc to include changes on Geometry and Coordinate
		RelativeLocation updatedRLoc2 = rlSvc.update(foundUpdatedRLoc);
		
		// 3.1: No longer needed, set foundRLoc to null
		foundUpdatedRLoc = null;
		
		// DB 3.1: Verify counts unchanged
		assertEquals(relativeLocationCountNew, rlSvc.count());
		assertEquals(geometryCountNew, geoSvc.count());
		assertEquals(coordinateCountNew, coordSvc.count());
		
		// 3.1: Verify updatedRLoc2
		assertNotNull(updatedRLoc2);
		assertEquals(newId, updatedRLoc2.getId());
		assertEquals(cityUpdated, updatedRLoc2.getCity());
		
		// 3.1: Verify foundRLoc's Geometry and Coordinate
		assertNotNull(updatedRLoc2.getGeometry());
		Geometry updatedRLocGeo2 = updatedRLoc2.getGeometry();
		assertNotNull(updatedRLocGeo2);
		
		// 3.1: Verify foundRLocGeo Coordinate
		assertEquals(numGeometryCoordinates, updatedRLocGeo2.getCoordinates().size());
		Coordinate updatedRLocGeoCoord2 = updatedRLocGeo2.getCoordinates().iterator().next();
		assertNotNull(updatedRLocGeoCoord2);
		assertEquals(latitudeUpdated, updatedRLocGeoCoord2.getLatitude());
		assertEquals(longitudeUpdated, updatedRLocGeoCoord2.getLongitude());
		
		// 3.1: No longer needed, set objects to null
		updatedRLoc2 = null;
		updatedRLocGeo2 = null;
		updatedRLocGeoCoord2 = null;
		
		// DB 3.2: *RETRIEVE* Find RelativeLocation, Geometry, and Coordinate in DB
		RelativeLocation foundUpdatedRLoc2 = rlSvc.findById(newId);
		Geometry foundUpdatedGeo = geoSvc.findById(newGeoId);
		Coordinate foundUpdatedCoord = coordSvc.findById(newCoordId);
		
		// DB 3.2: Verify counts unchanged
		assertEquals(relativeLocationCountNew, rlSvc.count());
		assertEquals(geometryCountNew, geoSvc.count());
		assertEquals(coordinateCountNew, coordSvc.count());
		
		// 3.2: Verify foundUpdatedRLoc2
		assertNotNull(foundUpdatedRLoc2);
		assertEquals(newId, foundUpdatedRLoc2.getId());
		assertEquals(cityUpdated, foundUpdatedRLoc2.getCity());
		
		// 3.2: Verify foundUpdatedGeo
		assertNotNull(foundUpdatedGeo);
		assertEquals(geometryTypeUpdated, foundUpdatedGeo.getType());
		assertEquals(numGeometryCoordinates, foundUpdatedGeo.getCoordinates().size());
		assertNotNull(foundUpdatedGeo.getRelativeLocations());
		assertTrue(foundUpdatedGeo.getRelativeLocations().size() > 0);
		assertTrue(foundUpdatedGeo.getRelativeLocations().size() == 1);
		assertNotNull(foundUpdatedGeo.getRelativeLocations().iterator().next());
		assertEquals(newId, foundUpdatedGeo.getRelativeLocations().iterator().next().getId());
		
		// 3.2: Verify foundRLocGeo Coordinate
		assertNotNull(foundUpdatedCoord);
		assertEquals(latitudeUpdated, foundUpdatedCoord.getLatitude());
		assertEquals(longitudeUpdated, foundUpdatedCoord.getLongitude());
		assertNotNull(foundUpdatedCoord.getGeometry());
		assertEquals(newGeoId, foundUpdatedCoord.getGeometry().getId());
		
		// 3.2: No longer needed, set objects to null
		foundUpdatedRLoc2 = null;
		foundUpdatedGeo = null;
		foundUpdatedCoord = null;
		
		// ******
		// STEP 4
		// ******
		
		// DB 4.1: *DELETE* Delete RelativeLocation in DB
		assertTrue(rlSvc.deleteById(newId));
		assertNull(geoSvc.findById(newGeoId));
		assertNull(coordSvc.findById(newCoordId));
		
		// DB 4.1: Verify counts unchanged
		assertEquals(relativeLocationCountInitial, rlSvc.count());
		assertEquals(geometryCountInitial, geoSvc.count());
		assertEquals(coordinateCountInitial, coordSvc.count());
	}

}
