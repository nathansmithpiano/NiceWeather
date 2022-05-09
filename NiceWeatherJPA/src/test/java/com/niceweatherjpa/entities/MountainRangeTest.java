package com.niceweatherjpa.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MountainRangeTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private MountainRange range;
	
	// Settings
	private final int rangeId = 2;
	private final String rangeName = "Rocky Mountains (CO)";
	private final int parentId = 1;
	private final String parentName = "Rocky Mountains";
	private final int subrangeCount = 8;
	private final int firstSubrangeId = 3;
	private final String firstSubrangeName = "Front";
	private final int rangeWithLocationId = 6;
	private final String rangeWithLocationName = "Sawatch";
	private final int rangeWithLocationCount = 159;
	private final int rangeLocationId = 1;
	private final String rangeLocationName = "Mt. Elbert";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("NiceWeatherJPA");
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}
	
	@BeforeEach
	void setUp() {
		em = emf.createEntityManager();
		range = em.find(MountainRange.class, rangeId);
		assertNotNull(range);
		assertEquals(rangeId, range.getId());
	}
	
	@AfterEach
	void tearDown() {
		em.close();
		range = null;
	}

	@Test
	@DisplayName("MountainRange mapping")
	void test_MountainRange_mapping() {
		assertEquals(rangeName, range.getName());
	}
	
	@Test
	@DisplayName("MountainRange MountainRange (subrange) mapping")
	void test_MountainRange_subrange_mapping() {
		assertNotNull(range.getSubranges());
		assertTrue(range.getSubranges().size() > 0);
		assertEquals(subrangeCount, range.getSubranges().size());
		
		// Get first subrange
		MountainRange subrange = range.getSubranges().iterator().next();
		assertNotNull(subrange);
		
		// Verify first subrange
		assertEquals(firstSubrangeId, subrange.getId());
		assertEquals(firstSubrangeName, subrange.getName());
		
		// Verify no subranges for subrange
		assertEquals(0, subrange.getSubranges().size());
	}
	
	@Test
	@DisplayName("MountainRange MountainRange (parent) mapping")
	void test_MountainRange_parent_mapping() {
		assertNotNull(range.getParent());
		assertEquals(parentId, range.getParent().getId());
		assertEquals(parentName, range.getParent().getName());
		
		// Get first subrange
		MountainRange subrange = range.getSubranges().iterator().next();
		assertNotNull(subrange);
		
		// Verify subrange's parent
		assertNotNull(subrange.getParent());
		assertEquals(rangeId, subrange.getParent().getId());
		assertEquals(rangeName, subrange.getParent().getName());
	}
	
	@Test
	@DisplayName("MountainRange Location mapping")
	void test_MountainRange_Location_mapping() {
		range = em.find(MountainRange.class, rangeWithLocationId);
		assertNotNull(range);
		assertEquals(rangeWithLocationName, range.getName());
		assertNotNull(range.getLocations());
		assertTrue(range.getLocations().size() > 0);
		assertEquals(rangeWithLocationCount, range.getLocations().size());
		
		// Get Location
		Location location = em.find(Location.class, rangeLocationId);
		assertNotNull(location);
		assertEquals(rangeLocationName, location.getName());
		
		// Verify MountainRange's locations contains Location
		assertTrue(range.getLocations().contains(location));
	}

}
