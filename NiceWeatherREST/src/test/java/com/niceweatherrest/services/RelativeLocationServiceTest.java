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
	
	// Settings
	private final int rLocId = 1;
	
	private List<RelativeLocation> relativeLocations;
	
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
		relativeLocations = rlSvc.index();
		assertNotNull(relativeLocations);
	}

	@AfterEach
	void tearDown() throws Exception {
		rlSvc = null;
		coordSvc = null;
		geoSvc = null;
	}
	
	@Test
	@DisplayName("RelativeLocationService index()")
	void test_index() {
		assertTrue(relativeLocations.size() > 0);
	}

	@Test
	@DisplayName("RelativeLocationService update()")
	void test_update() {
		// Change and revert city
		
		// Settings
		final String updatedCity = "Updated City";
		
		// Find RelativeLocation in DB
		RelativeLocation rLoc = rlSvc.findById(rLocId);
		assertNotNull(rLoc);
		final String initialCity = rLoc.getCity();
		
		// Update locally
		rLoc.setCity(updatedCity);
		
		// Update on DB
		RelativeLocation rLocUpdated = rlSvc.update(rLoc);
		assertNotNull(rLocUpdated);
		rLoc = null;
		
		// Verify
		assertEquals(rLocId, rLocUpdated.getId());
		RelativeLocation rLocFound = rlSvc.findById(rLocId);
		rLocUpdated = null;
		assertEquals(updatedCity, rLocFound.getCity());
		
		// Revert locally
		rLocFound.setCity(initialCity);
		
		// Revert on DB
		RelativeLocation rLocReverted = rlSvc.update(rLocFound);
		assertNotNull(rLocReverted);
		rLocFound = null;
		
		// Verify
		assertEquals(rLocId, rLocReverted.getId());
		RelativeLocation rLocFinal = rlSvc.findById(rLocId);
		assertNotNull(rLocFinal);
		rLocReverted = null;
		assertEquals(initialCity, rLocFinal.getCity());
		
		// Verify nothing new created
		assertEquals(relativeLocations.size(), rlSvc.index().size());
	}
	
	@Test
	@DisplayName("RelativeLocationService CR_D with Geometry, Coordinate")
	void test_CR_D() {
		// Create and delete Coordinate, Geometry, and RelativeLocation
		
		// Settings
		final String type = "Test Type";
		final double latitude = 12.34;
		final double longitude = -98.76;
		final int numCoordinates = 1;
		final int rlCount = rlSvc.index().size();
		final int coordinateCount = coordSvc.index().size();
		final int geometryCount = geoSvc.index().size();
		
		// Create Coordinate locally
		Coordinate coordinate = new Coordinate();
		coordinate.setLatitude(latitude);
		coordinate.setLongitude(longitude);
		
		// Create Geometry locally and add Coordinate
		Geometry geometry = new Geometry();
		geometry.addCoordinate(coordinate);
		
		// Create RelativeLocation locally and set Geometry
		RelativeLocation rlNew = new RelativeLocation();
		rlNew.setType(type);
		rlNew.setGeometry(geometry);
		
		// Persist all to DB
		RelativeLocation rlCreated = rlSvc.create(rlNew);
		assertNotNull(rlCreated);
		final int rlId = rlCreated.getId();
		
		// Verify Relative Location from DB
		RelativeLocation rlFound = rlSvc.findById(rlId);
		assertNotNull(rlFound);
		rlCreated = null;
		assertEquals(type, rlFound.getType());
		
		// Verify Geometry locally and from DB
		Geometry geoCreated = rlFound.getGeometry();
		assertNotNull(geoCreated);
		final int geoId = geoCreated.getId();
		Geometry geoFound = geoSvc.findById(geoId);
		assertNotNull(geoFound);
		geoCreated = null;
		assertEquals(geoFound, rlFound.getGeometry());
		// TODO: contained in geometry's rl set
		
		// Verify Coordinate locally and from DB
		assertEquals(numCoordinates, geoFound.getCoordinates().size());
		Coordinate coordCreated = geoFound.getCoordinates().iterator().next();
		assertNotNull(coordCreated);
		final int coordId = coordCreated.getId();
		Coordinate coordFound = coordSvc.findById(coordId);
		assertNotNull(coordFound);
		coordCreated = null;
		assertTrue(geoFound.getCoordinates().contains(coordFound));
		assertEquals(latitude, coordFound.getLatitude());
		assertEquals(longitude, coordFound.getLongitude());
		
		// Delete from DB
		assertTrue(rlSvc.deleteById(rlId));
		assertNull(rlSvc.findById(rlId));
		
		// Verify Geometry deleted
		assertNull(geoSvc.findById(geoId));
		assertFalse(geoSvc.index().contains(geoFound));
		
		// Verify Coordinate deleted
		assertNull(coordSvc.findById(coordId));
		assertFalse(coordSvc.index().contains(coordFound));
		
		// Verify no latitude in DB (probably excessive)
		List<Coordinate> list = coordSvc.index();
		boolean latFound = false;
		for (Coordinate coord : list) {
			if (coord.getLatitude() == latitude) {
				latFound = true;
			}
		}
		assertFalse(latFound);
		
		// Verify counts match for all tables
		assertEquals(rlCount, rlSvc.index().size());
		assertEquals(geometryCount, geoSvc.index().size());
		assertEquals(coordinateCount, coordSvc.index().size());
	}

}
