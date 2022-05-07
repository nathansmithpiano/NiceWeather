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

import com.niceweatherjpa.entities.MountainRange;

@SpringBootTest
class MountainRangeServiceTest {
	
	@Autowired
	private MountainRangeServiceImpl mrSvc;
	
	@Autowired
	private LocationServiceImpl locSvc;

	@BeforeEach
	void setUp() throws Exception {
		assertNotNull(mrSvc);
		assertNotNull(locSvc);
	}

	@AfterEach
	void tearDown() throws Exception {
		mrSvc = null;
		locSvc = null;
	}
	
	@Test
	@DisplayName("MountainRangeService index()")
	void test_index() {
		assertNotNull(mrSvc.index());
		assertTrue(mrSvc.index().size() > 0);
	}
	
	@Test
	@DisplayName("MountainRangeService update()")
	void test_update() {
		// Settings
		final int rangeId = 2;
		final String updatedName = "Updated";
		final int rangeCount = mrSvc.index().size();
		
		// Find MountainRange
		MountainRange range = mrSvc.findById(rangeId);
		assertNotNull(range);
		final String initialName = range.getName();
		
		// Find subranges
		assertNotNull(range.getSubranges());
		assertTrue(range.getSubranges().size() > 0);
		
		// Update locally
		range.setName(updatedName);
		// No changes yet
		assertEquals(initialName, mrSvc.findById(rangeId).getName());
		
		// Update on DB
		range = mrSvc.update(range);
		assertNotNull(range);
		assertEquals(rangeId, range.getId());
		assertEquals(updatedName, range.getName());
		assertEquals(updatedName, mrSvc.findById(rangeId).getName());

		// Revert locally
		range.setName(initialName);
		// No changes yet
		assertEquals(initialName, range.getName());

		// Revert on DB
		range = mrSvc.update(range);
		assertNotNull(range);
		assertEquals(rangeId, range.getId());
		assertEquals(initialName, range.getName());
		assertEquals(initialName, mrSvc.findById(rangeId).getName());

		// Verify nothing new created
		assertEquals(rangeCount, mrSvc.index().size());
	}
	
	@Test
	@DisplayName("MountainRangeService CRUD with self-join")
	void test_CRUD() {
		// Settings
		String newName = "New Name";
		final int parentId = 1;
		final int subId1 = 3;
		final int subId2 = 4;
		final int numSubs = 2;
		final int rangeCount = mrSvc.index().size();
		
		// Create new MountainRange locally
		MountainRange range = new MountainRange();
		range.setName(newName);
		
		// Set parent locally
		MountainRange parent = mrSvc.findById(parentId);
		assertNotNull(parent);
		range.setParent(parent);
		
		// Persist to DB
		MountainRange newRange = mrSvc.create(range);
		assertNotNull(newRange);
		final int newId = newRange.getId();
		newRange = mrSvc.findById(newId);
		
		// Set subranges locally before updating
		MountainRange sub1 = mrSvc.findById(subId1);
		assertNotNull(sub1);
		final int sub1ParentId = sub1.getParent().getId();
		sub1.setParent(newRange);
		newRange.addSubrange(sub1);
		MountainRange sub2 = mrSvc.findById(subId2);
		assertNotNull(sub2);
		final int sub2ParentId = sub2.getParent().getId();
		sub2.setParent(newRange);
		newRange.addSubrange(sub2);
		assertNotNull(newRange.getSubranges());
		assertEquals(numSubs, newRange.getSubranges().size());
		
		// Update DB
		MountainRange updatedRange = mrSvc.update(newRange);
		final int updatedId = updatedRange.getId();
		assertNotNull(updatedRange);
		assertEquals(newId, updatedId);
		
		// Verify new MountainRange
		assertEquals(rangeCount + 1, mrSvc.index().size());
		assertNotNull(newRange);
		assertTrue(newRange.getId() > 0);
		assertEquals(newName, newRange.getName());
		
		// Verify parent and subranges
		assertNotNull(newRange.getParent());
		assertEquals(parentId, newRange.getParent().getId());
		assertEquals(parent, newRange.getParent());
		assertNotNull(newRange.getSubranges());
		assertEquals(numSubs, newRange.getSubranges().size());
		assertTrue(newRange.getSubranges().contains(sub1));
		assertTrue(newRange.getSubranges().contains(sub2));
		
		// Revert subranges locally
		sub1 = mrSvc.findById(subId1);
		assertNotNull(sub1);
		assertEquals(updatedId, sub1.getParent().getId());
		sub2 = mrSvc.findById(subId2);
		assertNotNull(sub2);
		assertEquals(updatedId, sub2.getParent().getId());
		MountainRange sub1Parent = mrSvc.findById(sub1ParentId);
		assertNotNull(sub1Parent);
		sub1.setParent(sub1Parent);
		MountainRange sub2Parent = mrSvc.findById(sub2ParentId);
		assertNotNull(sub2Parent);
		sub2.setParent(sub2Parent);
		
		// Revert subranges in DB and verify
		MountainRange updatedSub1 = mrSvc.update(sub1);
		assertNotNull(updatedSub1);
		assertEquals(subId1, updatedSub1.getId());
		updatedSub1 = mrSvc.findById(subId1);
		assertNotNull(updatedSub1);
		assertNotNull(updatedSub1.getParent());
		assertEquals(sub1ParentId, updatedSub1.getParent().getId());
		MountainRange updatedSub2 = mrSvc.update(sub2);
		assertNotNull(updatedSub2);
		assertEquals(subId2, updatedSub2.getId());
		updatedSub2 = mrSvc.findById(subId2);
		assertNotNull(updatedSub2);
		assertNotNull(updatedSub2.getParent());
		assertEquals(sub2ParentId, updatedSub2.getParent().getId());
		
		// Delete range
		assertTrue(mrSvc.deleteById(updatedId));
		
		// Verify
		assertNull(mrSvc.findById(newId));
		assertEquals(rangeCount, mrSvc.index().size());
		sub1 = mrSvc.findById(subId1);
		assertNotNull(sub1);
		assertEquals(sub1ParentId, sub1.getParent().getId());
		
	}

	

}
