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
import org.junit.jupiter.api.Test;

class MountainRangeTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private MountainRange range;
	
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
		range = em.find(MountainRange.class, 1);
	}
	
	@AfterEach
	void tearDown() {
		em.close();
		range = null;
	}

	@Test
	void test_MountainRange_mapping() {
		assertNotNull(range);
		assertEquals(1, range.getId());
		assertEquals("Rocky Mountains", range.getName());
	}
	
	@Test
	void test_MountainRange_MountainRange_mapping() {
		assertNotNull(range); //Rocky Mountains
		assertNotNull(range.getSubranges());
		assertNull(range.getParent()); //no parent
		assertTrue(range.getSubranges().size() > 0);
		assertTrue(range.getSubranges().get(0).getSubranges().size() > 0);
		assertEquals(3, range.getSubranges().get(0).getSubranges().get(0).getId());
		assertEquals("Front", range.getSubranges().get(0).getSubranges().get(0).getName());
		range = em.find(MountainRange.class, 6); //Front
		assertNotNull(range.getSubranges());
		assertNotNull(range.getParent()); //Rocky Mountains (CO)
		assertEquals(2, range.getParent().getId());
		assertEquals("Rocky Mountains (CO)", range.getParent().getName());
		assertNotNull(range.getParent().getParent()); //Rocky Mountains
		assertEquals(1, range.getParent().getParent().getId());
		assertEquals("Rocky Mountains", range.getParent().getParent().getName());
	}
	
//	@Test
//	void test_MountainRange_Location_mapping() {
//		range = em.find(MountainRange.class, 6); //Sawatch
//		assertNotNull(range);
//		assertNotNull(range.getLocationList());
//		assertTrue(range.getLocationList().size() > 0);
//		assertEquals(1, range.getLocationList().get(0).getId());
//		assertEquals("Mt. Elbert", range.getLocationList().get(0).getName());
//	}

}
