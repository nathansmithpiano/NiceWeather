//package com.niceweatherjpa.entities;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
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
//class PeriodTest {
//
//	private static EntityManagerFactory emf;
//	private static EntityManager em;
//	private Period period;
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
//		period = em.find(Period.class, 1);
//	}
//	
//	@AfterEach
//	void tearDown() {
//		em.close();
//		period = null;
//	}
//
//	@Test
//	void test_Period_mapping() {
//		assertNotNull(period);
//		assertEquals(1, period.getId());
//		assertEquals(1, period.getNumber());
//		period = em.find(Period.class, 14);
//		assertEquals(14, period.getId());
//		assertEquals(14, period.getNumber());
//	}
//	
//	@Test
//	void test_Period_Forecast_mapping() {
//		assertNotNull(period);
//		assertNotNull(period.getForecast());
//		assertEquals(1, period.getForecast().getId());
//		assertEquals("https://api.weather.gov/gridpoints/PUB/33,107/forecast", period.getForecast().getUrl());
//		period = em.find(Period.class, 15);
//		assertNotNull(period.getForecast());
//		assertEquals(2, period.getForecast().getId());
//		assertEquals("https://api.weather.gov/gridpoints/PUB/33,107/forecast/hourly", period.getForecast().getUrl());
//	}
//
//}
