package com.niceweatherrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.niceweatherjpa.entities.Coordinate;
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
	@DisplayName("GeometryService count()")
	void test_index() {
		// Test if count() functions and DB has at least 1 Geometry
		assertNotNull(geoSvc.index());
		assertTrue(geoSvc.count() > 0);
	}

	@Test
	@DisplayName("GeometryService CRUD with Coordinates")
	void test_CRUD_with_Coordinates() {
		// 1: Create new Geometry with many Coordinates and verify
		// 2: Update Geometry's type and verify
		// 3: Update Geometry's Coordinates and verify
		// 4: Add More Coordinates and verify
		// 5: Delete all and verify

		// Settings
		final Long geometryCountInitial = geoSvc.count();
		final Long coordinateCountInitial = coordSvc.count();
		final String geometryType = "test new type";
		final String updatedType = "test updated type";
		final int numCoordinates = 100;
		final double latitude = 111.11;
		final double longitude = -222.22;
		final int numCoordinatesToUpdate = 17;
		final double updatedLatitude = 333.33;
		final double updatedLongitude = -444.44;
		final int numCoordinatesToAdd = 500;
		final double addedLatitude = 555.55;
		final double addedLongitude = -666.66;

		// Verify before test
		assertTrue(numCoordinatesToUpdate <= numCoordinates);

		// ******
		// STEP 1
		// ******

		// 1.1: Create Geometry locally
		Geometry geometry = new Geometry();
		geometry.setType(geometryType);

		// 1.1: Create Coordinates locally and add to Geometry locally
		for (int i = 0; i < numCoordinates; i++) {
			Coordinate coordinate = new Coordinate();
			coordinate.setLatitude(latitude);
			coordinate.setLongitude(longitude);
			geometry.addCoordinate(coordinate);
		}

		// 1.1: Verify Coordinates added locally
		assertEquals(numCoordinates, geometry.getCoordinates().size());

		// DB 1.1: *CREATE* Geometry and Coordinates to DB
		Geometry createdGeometry = geoSvc.create(geometry);

		// DB 1.1: Verify new counts in DB
		final long geometryCountNew = geoSvc.count();
		final long coordinateCountNew = coordSvc.count();
		assertEquals(geometryCountInitial + 1, geometryCountNew);
		assertEquals(coordinateCountInitial + numCoordinates, coordinateCountNew);

		// 1.1: Verify returned Geometry
		assertNotNull(createdGeometry);
		assertEquals(geometryType, createdGeometry.getType());
		assertEquals(numCoordinates, createdGeometry.getCoordinates().size());
		final int newId = createdGeometry.getId();

		// 1.1: Verify returned Geometry's Coordinates
		for (Coordinate coordinate : createdGeometry.getCoordinates()) {
			assertEquals(newId, coordinate.getGeometry().getId());
			assertEquals(geometryType, coordinate.getGeometry().getType());
			assertEquals(latitude, coordinate.getLatitude());
			assertEquals(longitude, coordinate.getLongitude());
		}

		// 1.1: No longer needed, set returned createdGeometry to null
		createdGeometry = null;

		// DB 1.2: *RETRIEVE* Find Geometry in DB
		Geometry foundGeometry = geoSvc.findById(newId);

		// DB 1.2: Verify counts unchanged
		assertEquals(geometryCountNew, geoSvc.count());
		assertEquals(coordinateCountNew, coordSvc.count());

		// 1.2: Verify foundGeometry
		assertNotNull(foundGeometry);
		assertEquals(newId, foundGeometry.getId());
		assertEquals(geometryType, foundGeometry.getType());
		assertEquals(numCoordinates, foundGeometry.getCoordinates().size());

		// 1.2: Verify foundGeometry's Coordinates
		for (Coordinate coordinate : foundGeometry.getCoordinates()) {
			assertEquals(newId, coordinate.getGeometry().getId());
			assertEquals(geometryType, coordinate.getGeometry().getType());
			assertEquals(latitude, coordinate.getLatitude());
			assertEquals(longitude, coordinate.getLongitude());
		}

		// ******
		// STEP 2
		// ******

		// 2.1: Update Geometry locally
		foundGeometry.setType(updatedType);

		// DB 2.1: *UPDATE* Update Geometry in DB
		Geometry updatedGeometry = geoSvc.update(foundGeometry);

		// 2.1: No longer needed, set foundGeometry to null
		foundGeometry = null;

		// DB 2.1: Verify counts unchanged
		assertEquals(geometryCountNew, geoSvc.count());
		assertEquals(coordinateCountNew, coordSvc.count());

		// 2.1: Verify returned updatedGeometry
		assertNotNull(updatedGeometry);
		assertEquals(newId, updatedGeometry.getId());
		assertEquals(updatedType, updatedGeometry.getType());
		assertEquals(numCoordinates, updatedGeometry.getCoordinates().size());

		// 2.1: Verify returned updatedGeometry's Coordinates
		for (Coordinate coordinate : updatedGeometry.getCoordinates()) {
			assertEquals(newId, coordinate.getGeometry().getId());
			assertEquals(updatedType, coordinate.getGeometry().getType());
			assertEquals(latitude, coordinate.getLatitude());
			assertEquals(longitude, coordinate.getLongitude());
		}

		// 2.1: No longer needed, set returned updatedGeometry to null
		updatedGeometry = null;

		// DB 2.2: *RETRIEVE* Find Geometry in DB
		Geometry foundUpdatedGeometry = geoSvc.findById(newId);

		// DB 2.2: Verify counts unchanged
		assertEquals(geometryCountNew, geoSvc.count());
		assertEquals(coordinateCountNew, coordSvc.count());

		// 2.2: Verify foundUpdatedGeometry
		assertNotNull(foundUpdatedGeometry);
		assertEquals(newId, foundUpdatedGeometry.getId());
		assertEquals(updatedType, foundUpdatedGeometry.getType());
		assertEquals(numCoordinates, foundUpdatedGeometry.getCoordinates().size());

		// 2.2: Verify foundUpdatedGeometry's Coordinates
		for (Coordinate coordinate : foundUpdatedGeometry.getCoordinates()) {
			assertEquals(newId, coordinate.getGeometry().getId());
			assertEquals(updatedType, coordinate.getGeometry().getType());
			assertEquals(latitude, coordinate.getLatitude());
			assertEquals(longitude, coordinate.getLongitude());
		}

		// ******
		// STEP 3
		// ******

		// 3.1: Update some of foundUpdatedGeometry's Coordinates
		Iterator<Coordinate> iterator1 = foundUpdatedGeometry.getCoordinates().iterator();
		for (int i = 0; i < numCoordinatesToUpdate; i++) {
			Coordinate coordinate = iterator1.next();
			coordinate.setLatitude(updatedLatitude);
			coordinate.setLongitude(updatedLongitude);
		}
		iterator1 = null;

		// 3.1: Verify foundUpdatedGeometry's updated Coordinates locally
		Iterator<Coordinate> iterator2 = foundUpdatedGeometry.getCoordinates().iterator();
		for (int i = 0; i < numCoordinatesToUpdate; i++) {
			Coordinate coordinate = iterator2.next();
			assertEquals(newId, coordinate.getGeometry().getId());
			assertEquals(updatedType, coordinate.getGeometry().getType());
			assertEquals(updatedLatitude, coordinate.getLatitude());
			assertEquals(updatedLongitude, coordinate.getLongitude());
		}
		iterator2 = null;

		// DB 3.1: *UPDATE* Update foundUpdatedGeometry and Coordinates in DB
		Geometry updatedGeometryUpdatedCoordinates = geoSvc.update(foundUpdatedGeometry);

		// 3.1: No longer needed, set foundUpdatedGeometry to null
		foundUpdatedGeometry = null;

		// DB 3.1: Verify counts unchanged
		assertEquals(geometryCountNew, geoSvc.count());
		assertEquals(coordinateCountNew, coordSvc.count());

		// 3.1: Verify returned updatedGeometryUpdatedCoordinates
		assertNotNull(updatedGeometryUpdatedCoordinates);
		assertEquals(newId, updatedGeometryUpdatedCoordinates.getId());
		assertEquals(updatedType, updatedGeometryUpdatedCoordinates.getType());
		assertEquals(numCoordinates, updatedGeometryUpdatedCoordinates.getCoordinates().size());

		// 3.1: Verify returned updatedGeometryUpdatedCoordinates's Coordinates
		int numChanged = 0;
		int numUnchanged = 0;
		for (Coordinate coordinate : updatedGeometryUpdatedCoordinates.getCoordinates()) {
			assertEquals(newId, coordinate.getGeometry().getId());
			assertEquals(updatedType, coordinate.getGeometry().getType());

			if (coordinate.getLatitude() == updatedLatitude && coordinate.getLongitude() == updatedLongitude) {
				numChanged++;
			}
			if (coordinate.getLatitude() == latitude && coordinate.getLongitude() == longitude) {
				numUnchanged++;
			}
		}
		assertEquals(numChanged, numCoordinatesToUpdate);
		assertEquals(numUnchanged, numCoordinates - numCoordinatesToUpdate);

		// 3.1 No longer needed, set returned updatedGeometryUpdatedCoordinates to null
		updatedGeometryUpdatedCoordinates = null;

		// DB 3.2: *RETRIEVE* Find Geometry in DB
		Geometry foundUpdatedGeometryUpdatedCoordinates = geoSvc.findById(newId);

		// DB 3.2: Verify counts unchanged
		assertEquals(geometryCountNew, geoSvc.count());
		assertEquals(coordinateCountNew, coordSvc.count());

		// 3.2: Verify returned foundUpdatedGeometryUpdatedCoordinates
		assertNotNull(foundUpdatedGeometryUpdatedCoordinates);
		assertEquals(newId, foundUpdatedGeometryUpdatedCoordinates.getId());
		assertEquals(updatedType, foundUpdatedGeometryUpdatedCoordinates.getType());
		assertEquals(numCoordinates, foundUpdatedGeometryUpdatedCoordinates.getCoordinates().size());

		// 3.2: Verify returned foundUpdatedGeometryUpdatedCoordinates's Coordinates
		numChanged = 0;
		numUnchanged = 0;
		for (Coordinate coordinate : foundUpdatedGeometryUpdatedCoordinates.getCoordinates()) {
			assertEquals(newId, coordinate.getGeometry().getId());
			assertEquals(updatedType, coordinate.getGeometry().getType());

			if (coordinate.getLatitude() == updatedLatitude && coordinate.getLongitude() == updatedLongitude) {
				numChanged++;
			}
			if (coordinate.getLatitude() == latitude && coordinate.getLongitude() == longitude) {
				numUnchanged++;
			}
		}
		assertEquals(numChanged, numCoordinatesToUpdate);
		assertEquals(numUnchanged, numCoordinates - numCoordinatesToUpdate);

		// ******
		// STEP 4
		// ******

		// 4.1: Create more Coordinates locally and add to returned
		// foundUpdatedGeometryUpdatedCoordinates locally
		for (int i = 0; i < numCoordinatesToAdd; i++) {
			Coordinate coordinate = new Coordinate();
			coordinate.setLatitude(addedLatitude);
			coordinate.setLongitude(addedLongitude);
			foundUpdatedGeometryUpdatedCoordinates.addCoordinate(coordinate);
		}

		// 4.1: Verify Coordinates added locally
		assertEquals(numCoordinates + numCoordinatesToAdd,
				foundUpdatedGeometryUpdatedCoordinates.getCoordinates().size());

		// DB 4.1: *UPDATE* Update returned foundUpdatedGeometry and Coordinates in DB
		Geometry geometryWithMoreCoordinates = geoSvc.update(foundUpdatedGeometryUpdatedCoordinates);

		// 4.1: No longer needed, set returned foundUpdatedGeometryUpdatedCoordinates to
		// null
		foundUpdatedGeometryUpdatedCoordinates = null;

		// DB 4.1: Verify counts unchanged
		assertEquals(geometryCountNew, geoSvc.count());
		assertEquals(coordinateCountNew + numCoordinatesToAdd, coordSvc.count());

		// 4.1: Verify returned updatedGeometry
		assertNotNull(geometryWithMoreCoordinates);
		assertEquals(newId, geometryWithMoreCoordinates.getId());
		assertEquals(updatedType, geometryWithMoreCoordinates.getType());
		assertEquals(numCoordinates + numCoordinatesToAdd, geometryWithMoreCoordinates.getCoordinates().size());

		// 4.1: Verify returned foundUpdatedGeometryUpdatedCoordinates's Coordinates
		numChanged = 0;
		numUnchanged = 0;
		int numNew = 0;
		for (Coordinate coordinate : geometryWithMoreCoordinates.getCoordinates()) {
			assertEquals(newId, coordinate.getGeometry().getId());
			assertEquals(updatedType, coordinate.getGeometry().getType());

			if (coordinate.getLatitude() == updatedLatitude && coordinate.getLongitude() == updatedLongitude) {
				numChanged++;
			}
			if (coordinate.getLatitude() == latitude && coordinate.getLongitude() == longitude) {
				numUnchanged++;
			}
			if (coordinate.getLatitude() == addedLatitude && coordinate.getLongitude() == addedLongitude) {
				numNew++;
			}
		}
		assertEquals(numChanged, numCoordinatesToUpdate);
		assertEquals(numUnchanged, numCoordinates - numCoordinatesToUpdate);
		assertEquals(numNew, numCoordinatesToAdd);

		// 4.1 No longer needed, set returned updatedGeometryUpdatedCoordinates to null
		geometryWithMoreCoordinates = null;

		// DB 4.2: *RETRIEVE* Find Geometry in DB
		Geometry foundGeometryWithMoreCoordinates = geoSvc.findById(newId);

		// DB 4.2: Verify counts unchanged
		assertEquals(geometryCountNew, geoSvc.count());
		assertEquals(coordinateCountNew + numCoordinatesToAdd, coordSvc.count());

		// 4.2: Verify returned updatedGeometry
		assertNotNull(foundGeometryWithMoreCoordinates);
		assertEquals(newId, foundGeometryWithMoreCoordinates.getId());
		assertEquals(updatedType, foundGeometryWithMoreCoordinates.getType());
		assertEquals(numCoordinates + numCoordinatesToAdd, foundGeometryWithMoreCoordinates.getCoordinates().size());

		// 4.2: Verify returned foundUpdatedGeometryUpdatedCoordinates's Coordinates
		numChanged = 0;
		numUnchanged = 0;
		numNew = 0;
		for (Coordinate coordinate : foundGeometryWithMoreCoordinates.getCoordinates()) {
			assertEquals(newId, coordinate.getGeometry().getId());
			assertEquals(updatedType, coordinate.getGeometry().getType());

			if (coordinate.getLatitude() == updatedLatitude && coordinate.getLongitude() == updatedLongitude) {
				numChanged++;
			}
			if (coordinate.getLatitude() == latitude && coordinate.getLongitude() == longitude) {
				numUnchanged++;
			}
			if (coordinate.getLatitude() == addedLatitude && coordinate.getLongitude() == addedLongitude) {
				numNew++;
			}
		}
		assertEquals(numChanged, numCoordinatesToUpdate);
		assertEquals(numUnchanged, numCoordinates - numCoordinatesToUpdate);
		assertEquals(numNew, numCoordinatesToAdd);

		// 4.1 No longer needed, set foundGeometryWithMoreCoordinates to null
		foundGeometryWithMoreCoordinates = null;

		// ******
		// STEP 5
		// ******

		// DB 5.1: Delete Geometry and all Coordinates from DB
		assertTrue(geoSvc.deleteById(newId));

		// DB 5.1: Verify counts unchanged
		assertEquals(geometryCountInitial, geoSvc.count());
		assertEquals(coordinateCountInitial, coordSvc.count());
	}

}