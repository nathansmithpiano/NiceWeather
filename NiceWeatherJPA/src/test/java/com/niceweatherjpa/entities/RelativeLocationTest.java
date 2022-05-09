//package com.niceweatherjpa.entities;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class RelativeLocationTest {
//
//	private static EntityManagerFactory emf;
//	private static EntityManager em;
//	private RelativeLocation rLoc;
//	
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//		emf = Persistence.createEntityManagerFactory("NiceWeatherJPA");
//	}
//	
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//		emf.close();
//	}
//	
//	@BeforeEach
//	void setUp() {
//		em = emf.createEntityManager();
//		rLoc = em.find(RelativeLocation.class, 1);
//	}
//	
//	@AfterEach
//	void tearDown() {
//		em.close();
//		rLoc = null;
//	}
//
//	@Test
//	void test_RelativeLocation_mapping() {
//		assertNotNull(rLoc);
//		assertEquals(1, rLoc.getId());
//		assertEquals("Twin Lakes", rLoc.getCity());
//	}
//	
//	@Test
//	void test_RelativeLocation_Geometry_mapping() {
//		assertNotNull(rLoc);
//		assertNotNull(rLoc.getGeometry());
//		assertEquals(2, rLoc.getGeometry().getId());
//	}
//	
//	@Test
//	void test_RelativeLocation_Point_mapping() {
//		assertNotNull(rLoc);
//		assertNotNull(rLoc.getPointList());
//		assertTrue(rLoc.getPointList().size() > 0);
//		assertEquals("https://api.weather.gov/points/39.1177,-106.4453", rLoc.getPointList().get(0).getIdUrl());
//	}
//
//}
