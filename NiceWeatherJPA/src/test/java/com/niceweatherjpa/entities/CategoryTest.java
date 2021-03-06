package com.niceweatherjpa.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Category category;
	
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
		category = em.find(Category.class, 1);
	}
	
	@AfterEach
	void tearDown() {
		em.close();
		category = null;
	}

	@Test
	void test_Category_mapping() {
		assertNotNull(category);
		assertEquals(1, category.getId());
		assertEquals("Peak", category.getName());
	}
	
	@Test
	void test_Category_Location_mapping() {
		assertNotNull(category);
		assertNotNull(category.getLocationList());
		assertTrue(category.getLocationList().size() > 0);
		assertEquals(1, category.getLocationList().get(0).getId());
		assertEquals("Mt. Elbert", category.getLocationList().get(0).getName());
	}

}
