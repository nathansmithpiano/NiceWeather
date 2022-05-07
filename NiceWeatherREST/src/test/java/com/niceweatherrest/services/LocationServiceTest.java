package com.niceweatherrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.niceweatherjpa.entities.Coordinate;
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

	@BeforeEach
	void beforeEach() {
		assertNotNull(locSvc);
	}
	
	@Test
	void test_update_location() {
		//settings
		final int idInit = 1;
		final String strNew = "Updated";
		final int locationCount = locSvc.index().size();
		
		//initial object(s)
		Location location = locSvc.findById(idInit);
		assertNotNull(location);
		
		//used for changes
		final String strInit = location.getName();
		
		//change locally
		assertEquals(strInit, location.getName());
		location.setName(strNew);
		assertEquals(strInit, locSvc.findById(idInit).getName()); //no changes yet
		
		//update
		location = locSvc.update(location);
		assertNotNull(location);
		assertEquals(idInit, location.getId());
		assertEquals(strNew, location.getName());
		assertEquals(strNew, locSvc.findById(idInit).getName()); //changes made
		
		//change back locally
		location.setName(strInit);
		assertEquals(strInit, location.getName()); // no changes yet
		
		//update
		location = locSvc.update(location);
		assertNotNull(location);
		assertEquals(idInit, location.getId());
		assertEquals(strInit, location.getName());
		assertEquals(strInit, locSvc.findById(idInit).getName()); //changed
		
		//verify
		assertEquals(locationCount, locSvc.index().size());
	}
	
	@Test
	void test_create_and_delete() {
		//settings
		String newName = "New Name";
		final int mtnRangeId = 9;
		
		//initial
		final int numLocations = locSvc.index().size();
		final int numCoordinates = coordSvc.index().size();
		final int numMountainRanges = mrSvc.index().size();
		
		//create new Location
		Location location = new Location();
		location.setName(newName);
		
		//set MountainRange and set to both
		MountainRange mountainRange = mrSvc.findById(mtnRangeId);
		assertNotNull(mountainRange);
		final int mountainRangeLocationCount;
		if (mountainRange.getLocations() == null) {
			mountainRangeLocationCount = 0;
		} else {
			mountainRangeLocationCount = mountainRange.getLocations().size();
		}
		mountainRange.addLocation(location);
		location.setMountainRange(mountainRange);
		
		//create new Coordinate (non-null) and set to both
		Coordinate coordinate = new Coordinate();
		coordinate.setLatitude(12.34);
		coordinate.setLongitude(-98.76);
		coordinate.addLocation(location);
		location.setCoordinate(coordinate);
		
		//create Location in DB
		Location newLocation = locSvc.create(location);
		assertNotNull(newLocation);
		final int createdId = newLocation.getId();
		
		//verify Location
		assertEquals(numLocations + 1, locSvc.index().size()); //1 more in DB
		location = locSvc.findById(createdId);
		assertNotNull(location);
		assertTrue(locSvc.index().contains(location));
		assertEquals(newName, location.getName());
		
		//verify MountainRange
		assertEquals(numMountainRanges, mrSvc.index().size());
		assertTrue(mrSvc.index().contains(mountainRange));
		assertNotNull(location.getMountainRange());
		assertEquals(mountainRange, location.getMountainRange());
		assertNotNull(mountainRange.getLocations());
		assertTrue(mountainRange.getLocations().size() > 0);
		assertEquals(mountainRangeLocationCount + 1, mountainRange.getLocations().size());
		assertTrue(mountainRange.getLocations().contains(location));
		
		//verify Coordinate
		assertEquals(numCoordinates + 1, coordSvc.index().size());
		assertTrue(coordSvc.index().contains(coordinate)); //coordinate created successfully
		assertNotNull(newLocation.getCoordinate());
		assertEquals(coordinate, location.getCoordinate());
		assertNotNull(coordinate.getLocations());
		assertTrue(coordinate.getLocations().size() > 0);
		assertNotNull(coordinate.getLocations().get(0));
		assertEquals(createdId, coordinate.getLocations().get(0).getId());
		assertEquals(location, coordinate.getLocations().get(0));
		assertTrue(coordinate.getLocations().contains(location));
		
		//delete Location
		assertTrue(locSvc.deleteById(createdId));
		assertEquals(numLocations, locSvc.index().size());
		
		//verify MountainRange updated
		assertEquals(numMountainRanges, mrSvc.index().size());
		mountainRange = mrSvc.findById(mtnRangeId);
		assertEquals(mountainRangeLocationCount, mountainRange.getLocations().size());
		assertFalse(mountainRange.getLocations().contains(location));
		
		//verify Coordinate also deleted
		assertEquals(numCoordinates, coordSvc.index().size());
		coordinate = coordSvc.findById(coordinate.getId());
		assertNull(coordinate);
		
	}
	
	
	

//	@Test
//	void test_create_location_and_Coordinate_MountainRange_Category_mappings() {
//		// Create new location "Mt. Massive"
//		Location newLoc = new Location();
//		newLoc.setName("Mt. Massive");
//		newLoc.setElevation(4395.5208);
////		
//		// Add MountainRange
//		MountainRange range = mrSvc.findById(6);
//		assertNotNull(range);
//		assertEquals("Sawatch", range.getName());
//		newLoc.setMountainRange(range);
//
//		// Add Coordinate
//		Coordinate coordinate = new Coordinate();
//		coordinate.setLatitude(39.187298);
//		coordinate.setLongitude(-106.475548);
//		newLoc.setCoordinate(coordinate);
//
//		// Add Categories
//		Category category1 = catSvc.findById(1);
//		assertNotNull(category1);
//		Category category3 = catSvc.findById(3);
//		assertNotNull(category3);
//		newLoc.addCategory(category1);
//		newLoc.addCategory(category3);
//
//		// Persist
//		Location createdLoc = locSvc.create(newLoc);
//		assertNotNull(createdLoc);
//		createdLoc = locSvc.findById(createdLoc.getId());
//		assertNotNull(createdLoc);
//
//		// Verify Coordinate persisted and mapped with location
//		Coordinate createdCoordinate = createdLoc.getCoordinate();
//		assertEquals(1, createdCoordinate.getLocationList().size());
//		assertNotNull(createdCoordinate);
//		createdCoordinate = coordSvc.findById(createdCoordinate.getId());
//		assertEquals(1, createdCoordinate.getLocationList().size()); //this is adding to its location list, why?
//		assertNotNull(createdCoordinate);
//		assertEquals(39.187298, createdCoordinate.getLatitude());
//		assertEquals(-106.475548, createdCoordinate.getLongitude());
//		assertNotNull(createdCoordinate.getLocationList());
//		assertTrue(createdCoordinate.getLocationList().size() > 0);
//		
//		assertTrue(createdCoordinate.getLocationList().contains(createdLoc));
//
//		// Verify MountainRange mapped with location
//		MountainRange mappedRange = createdLoc.getMountainRange();
//		assertNotNull(mappedRange);
//		mappedRange = mrSvc.findById(mappedRange.getId());
//		assertNotNull(mappedRange);
//		assertEquals("Sawatch", mappedRange.getName());
//		assertNotNull(mappedRange.getLocationList());
//		assertTrue(mappedRange.getLocationList().size() > 0);
//		assertTrue(mappedRange.getLocationList().contains(createdLoc));
//
//		// Verify Categories mapped with location
//		assertNotNull(createdLoc.getCategories());
//		assertTrue(createdLoc.getCategories().size() > 0);
//		assertTrue(createdLoc.getCategories().size() == 2);
//		category1 = createdLoc.getCategories().get(0);
//		assertNotNull(category1);
//		category1 = catSvc.findById(category1.getId());
//		assertNotNull(category1);
//		assertNotNull(category1.getLocationList());
//		assertTrue(category1.getLocationList().contains(createdLoc));
//		category3 = createdLoc.getCategories().get(1);
//		assertNotNull(category3);
//		category3 = catSvc.findById(category3.getId());
//		assertNotNull(category3);
//		assertNotNull(category3.getLocationList());
//		assertTrue(category3.getLocationList().contains(createdLoc));
//		
//		//Update Location
//		Location oldLocation = locSvc.findById(createdLoc.getId());
//		oldLocation.setName("Updated Name");
//		Location updatedLocation = locSvc.update(oldLocation);
//		assertNotNull(updatedLocation);
//		assertEquals(oldLocation.getId(), updatedLocation.getId());
//		assertEquals("Updated Name", updatedLocation.getName());
//		updatedLocation = locSvc.findById(updatedLocation.getId());
//		assertNotNull(updatedLocation);
//		assertEquals(oldLocation.getId(), updatedLocation.getId());
//		assertEquals("Updated Name", updatedLocation.getName());
//		
//		//Verify Coordinate updated
//		Coordinate updatedCoordinate = updatedLocation.getCoordinate();
//		assertNotNull(updatedCoordinate);
//		assertEquals(14, updatedCoordinate.getId());
//		assertNotNull(updatedCoordinate.getLocationList());
//		assertTrue(createdCoordinate.getLocationList().size() > 0);
//		assertTrue(createdCoordinate.getLocationList().contains(updatedLocation));
//		assertEquals(1, createdCoordinate.getLocationList().size());
//		
//		//TODO : it is adding another coordinate when updating, not sure why...
//		
////		assertTrue(createdCoordinate.getLocationList().size() == 1);
//	}

}
