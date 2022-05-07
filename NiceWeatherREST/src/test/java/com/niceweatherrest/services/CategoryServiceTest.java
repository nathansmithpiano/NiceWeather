package com.niceweatherrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.niceweatherjpa.entities.Category;
import com.niceweatherjpa.entities.Location;

@SpringBootTest
class CategoryServiceTest {

	@Autowired
	private CategoryServiceImpl catSvc;

	@Autowired
	private LocationServiceImpl locSvc;

	@Test
	void test_CategoryService_index() {
		assertNotNull(catSvc);
		assertNotNull(catSvc.index());
		assertTrue(catSvc.index().size() > 0);
		assertEquals(1, catSvc.index().get(0).getId());
	}

	@Test
	void test_CategoryService_create_update_delete() {
		assertNotNull(catSvc);
		// Create new Category
		Category newCat = new Category();
		newCat.setName("Test Name");
		// Persist in database and return newly created Category
		Category created = catSvc.create(newCat);
		newCat = null;
		assertNotNull(created);
		assertEquals("Test Name", created.getName());
		// Update newly created Category and return updated Category
		created.setName("Updated Name");
		Category updated = catSvc.update(created);
		assertNotNull(updated);
		assertEquals(created.getId(), updated.getId());
		assertEquals("Updated Name", updated.getName());
		created = null;
		// Delete updated Category
		int oldId = updated.getId();
		catSvc.delete(updated);
		assertNull(catSvc.findById(oldId));
	}

	@Test
	void test_CategoryService_update_changes_Location() {
		assertNotNull(catSvc);
		Location loc = locSvc.findById(1); // Mt. Elbert
		assertNotNull(loc.getCategories());
		assertTrue(loc.getCategories().size() > 0);
		assertNotNull(loc.getCategories().get(0));
		Category cat = loc.getCategories().get(0);
		assertNotNull(cat);
		cat.setName("Updated Name");
		Category updatedCat = catSvc.update(cat);
		assertNotNull(updatedCat);
		assertEquals("Updated Name", updatedCat.getName());
		assertEquals("Updated Name", loc.getCategories().get(0).getName());
	}

}
