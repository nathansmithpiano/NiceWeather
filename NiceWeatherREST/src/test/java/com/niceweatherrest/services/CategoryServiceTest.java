package com.niceweatherrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

	@BeforeEach
	void setUp() throws Exception {
		assertNotNull(catSvc);
		assertNotNull(locSvc);
	}

	@AfterEach
	void tearDown() throws Exception {
		catSvc = null;
		locSvc = null;
	}

	@Test
	@DisplayName("CategoryService index()")
	void test_index() {
		assertNotNull(catSvc.index());
		assertTrue(catSvc.index().size() > 0);
	}

	@Test
	@DisplayName("CategoryService update()")
	void test_update() {
		// Update and restore name

		// Settings
		final int locId = 1;
		final String updatedName = "Updated";
		final int categoryCount = catSvc.index().size();
		final int locationCount = locSvc.index().size();

		// Find Location
		Location location = locSvc.findById(locId);
		assertNotNull(location);

		// Find Category
		assertNotNull(location.getCategories());
		assertTrue(location.getCategories().size() > 0);
		Category category = location.getCategories().iterator().next();
		final int catId = category.getId();
		final String initialName = category.getName();

		// Update locally
		category.setName(updatedName);
		// No changes yet
		assertEquals(initialName, catSvc.findById(catId).getName());

		// Update on DB
		category = catSvc.update(category);
		assertNotNull(category);
		assertEquals(catId, category.getId());
		assertEquals(updatedName, category.getName());
		assertEquals(updatedName, catSvc.findById(catId).getName());

		// Revert locally
		category.setName(initialName);
		// No changes yet
		assertEquals(initialName, category.getName());

		// Revert on DB
		category = catSvc.update(category);
		assertNotNull(category);
		assertEquals(catId, category.getId());
		assertEquals(initialName, category.getName());
		assertEquals(initialName, catSvc.findById(catId).getName());

		// Verify nothing new created
		assertEquals(categoryCount, catSvc.index().size());
		assertEquals(locationCount, locSvc.index().size());
	}

	@Test
	@DisplayName("CategoryService create, read, delete without locations")
	void test_CR_D() {
		// Create and delete new Category

		// Settings
		final String newName = "New Category Name";
		final int categoriesCount = catSvc.index().size();

		// Create new Category locally
		Category category = new Category();
		category.setName(newName);

		// Persist to DB
		Category newCategory = catSvc.create(category);
		final int newId = newCategory.getId();
		assertNotNull(newCategory);

		// Verify Category
		assertEquals(categoriesCount + 1, catSvc.index().size());
		assertEquals(newName, newCategory.getName());
		newCategory = catSvc.findById(newId);
		assertNotNull(newCategory);
		assertEquals(newName, newCategory.getName());

		// Delete from DB
		assertTrue(catSvc.deleteById(newId));
		assertNull(catSvc.findById(newId));
	}

//	@Test
//	@DisplayName("CategoryService delete, delete from join table")
//	void test_delete_join_table() {
//		
//		
//		// Settings
//		final int categoryId = 1; // "Peak"
//		
//		// Find Category
//		final Category backup = catSvc.findById(categoryId);
//		assertNotNull(backup);
//		
//		// Get Category locations
//		assertNotNull(backup.getLocations());
//		final Set<Location> initialLocations = backup.getLocations();
//		assertNotNull(initialLocations);
//		
//		// Delete Category
//		assertTrue(catSvc.deleteById(categoryId));
//		
//		// Verify
//		assertFalse(catSvc.index().contains(backup));
//		
//		// NOTE: on deleting, join table references are deleted, locations no longer contain category
//		// Should not allow anyone to delete a Category but it is possible
//		// Commenting out this test so it doesn't interfere with DB ids
//		
//	}

}
