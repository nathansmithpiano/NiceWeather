package com.niceweatherrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.niceweatherjpa.entities.Coordinate;
import com.niceweatherjpa.entities.Geometry;

@SpringBootTest
class TemporaryTests {
	
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
	@DisplayName("Geometry with many Coordinate persist only Geometry")
	void test_index() {
		//Settings
		final int numCoordinates = 50;
		final String geometryType = "Temporary Type";
		final int coordinateCountInit = coordSvc.index().size();
		
		// Create new Geometry
		Geometry newGeo = new Geometry();
		newGeo.setType(geometryType);
		final double latIndexMultiplier = 1.111111;
		final double longIndexMultiplier = -1.111111;
		
		List<Coordinate> newCoordinates = new ArrayList<>();
		for (int i = 1; i <= numCoordinates; i++) {
			Coordinate coord = new Coordinate();
			coord.setLatitude(i * latIndexMultiplier);
			coord.setLongitude(i * longIndexMultiplier);
			newCoordinates.add(coord);
			newGeo.addCoordinate(coord);
		}
		
		// Verify locally
		assertEquals(numCoordinates, newGeo.getCoordinates().size());
		
		// Persist to DB
		Geometry createdGeo = geoSvc.create(newGeo);
		assertNotNull(createdGeo);
		final int geoId = createdGeo.getId();
		createdGeo = null;
		Geometry foundGeo = geoSvc.findById(geoId);
		assertNotNull(foundGeo);
		
		// Verify Geometry
		assertEquals(geometryType, foundGeo.getType());
		assertEquals(numCoordinates, foundGeo.getCoordinates().size());
		
		// Verify Coordinates
		assertEquals(coordinateCountInit + numCoordinates, coordSvc.index().size());
	}

}
