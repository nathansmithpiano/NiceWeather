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

import com.niceweatherjpa.entities.Geometry;

@SpringBootTest
class GeometryServiceTest {

	@Autowired
	private GeometryServiceImpl geoSvc;

	@Autowired
	private CoordinateServiceImpl coordSvc;

	@Autowired
	private LocationServiceImpl locSvc;

	@BeforeEach
	void setUp() throws Exception {
		assertNotNull(geoSvc);
		assertNotNull(coordSvc);
		assertNotNull(locSvc);
	}

	@AfterEach
	void tearDown() throws Exception {
		geoSvc = null;
		coordSvc = null;
		locSvc = null;
	}

	@Test
	@DisplayName("GeometryService index()")
	void test_index() {
		assertNotNull(geoSvc.index());
		assertTrue(geoSvc.index().size() > 0);
	}

	@Test
	@DisplayName("GeometryService update()")
	void test_update() {
		// Change and revert type

		// Settings
		final int geoId = 1;
		final String updatedType = "Updated";
		final int geoCount = geoSvc.index().size();

		// Find Location
		Geometry geometry = geoSvc.findById(geoId);
		assertNotNull(geometry);
		final String initialType = geometry.getType();

		// Update locally
		geometry.setType(updatedType);

		// Update on DB
		Geometry updatedGeo = geoSvc.update(geometry);
		assertNotNull(updatedGeo);
		geometry = null;

		// Verify in DB
		assertEquals(updatedType, updatedGeo.getType());
		assertEquals(geoId, updatedGeo.getId());
		Geometry foundGeo = geoSvc.findById(geoId);
		assertEquals(foundGeo, updatedGeo);
		updatedGeo = null;
		assertEquals(updatedType, foundGeo.getType());

		// Revert locally
		foundGeo.setType(initialType);

		// Revert on DB
		Geometry revertedGeo = geoSvc.update(foundGeo);
		assertNotNull(revertedGeo);
		assertEquals(geoId, revertedGeo.getId());
		assertEquals(initialType, revertedGeo.getType());

		// Verify nothing new created
		assertEquals(geoCount, geoSvc.index().size());

	}

}
