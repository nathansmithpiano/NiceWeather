package com.niceweatherrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

	// Settings
	final private int rangeId = 2;
	final private String updatedName = "Updated";

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
		MountainRange updatedRange = mrSvc.update(range);
		range = null;
		assertNotNull(updatedRange);
		assertEquals(rangeId, updatedRange.getId());
		assertEquals(updatedName, updatedRange.getName());
		assertEquals(updatedName, mrSvc.findById(rangeId).getName());

		// Revert locally
		updatedRange.setName(initialName);
		// No changes yet
		assertEquals(initialName, updatedRange.getName());

		// Revert on DB
		MountainRange revertedRange = mrSvc.update(updatedRange);
		range = null;
		assertNotNull(revertedRange);
		assertEquals(rangeId, revertedRange.getId());
		assertEquals(initialName, revertedRange.getName());
		assertEquals(initialName, mrSvc.findById(rangeId).getName());

		// Verify nothing new created
		assertEquals(rangeCount, mrSvc.index().size());
	}

	@Test
	@DisplayName("MountainRangeService create, read, delete without subranges")
	void test_CR_D_without_subranges() {
		// Create new MountainRange, set parent, persist, remove new MountainRange

		// Settings
		String newName = "New Name";
		final int parentId = 2;

		// Create locally
		MountainRange newRange = new MountainRange();
		newRange.setName(newName);

		// Find parent and set to newRange
		MountainRange parent = mrSvc.findById(parentId);
		assertNotNull(parent);
		final int parentSubrangeCount = parent.getSubranges().size();
		newRange.setParent(parent);

		// Persist to DB
		MountainRange createdRange = mrSvc.create(newRange);
		newRange = null;
		assertNotNull(createdRange);
		final int createdId = createdRange.getId();
		createdRange = null;

		// Find new MountainRange in DB
		MountainRange foundRange = mrSvc.findById(createdId);
		assertNotNull(foundRange);
		assertEquals(newName, foundRange.getName());
		assertEquals(parent, foundRange.getParent());

		// Find parent in DB
		MountainRange updatedParent = mrSvc.findById(parentId);
		parent = null;
		assertNotNull(updatedParent);

		// Verify parent is foundRange's parent
		assertEquals(updatedParent, foundRange.getParent());

		// Verify parent has foundRange in its subranges
		assertTrue(updatedParent.getSubranges().contains(foundRange));

		// Remove subrange from parent locally
		updatedParent.removeSubrange(foundRange);

		// Verify updates locally
		assertNotEquals(updatedParent, foundRange.getParent());
		assertFalse(updatedParent.getSubranges().contains(foundRange));

		// Delete on DB
		assertTrue(mrSvc.deleteById(createdId));

		// Find parent on DB
		MountainRange updatedParent2 = mrSvc.findById(parentId);

		// Verify updated parent does not contain foundRange within subranges
		assertNotNull(updatedParent2.getSubranges());
		assertFalse(updatedParent2.getSubranges().contains(foundRange));

		// Verify range no longer in DB
		assertNull(mrSvc.findById(createdId));

		// Verify parent returns to same number of subranges
		assertEquals(parentSubrangeCount, updatedParent2.getSubranges().size());
	}

	@Test
	@DisplayName("MountainRangeService update with subrange changes")
	void test_update_with_subranges() {
		// Change subranges, parents, and revert

		// Settings
		final int rangeId = 3; // "Front"
		final int rangeSubrangeCount = 0; // has no subranges initially
		final int numSubrangesToAdd = 4;
		final int numRanges = mrSvc.index().size();

		// Find range in DB
		MountainRange range = mrSvc.findById(rangeId);
		assertNotNull(range);
		assertEquals(rangeSubrangeCount, range.getSubranges().size());

		// Get parent
		MountainRange parent = range.getParent();
		assertNotNull(parent);
		final int parentId = parent.getId();
		parent = null;

		// Find parent in DB
		parent = mrSvc.findById(parentId);
		assertNotNull(parent);

		// Verify parent range connections
		assertTrue(parent.getSubranges().contains(range));
		assertEquals(parent, range.getParent());

		// Find other ranges in DB
		MountainRange range5 = mrSvc.findById(5);
		assertNotNull(range5);
		MountainRange range6 = mrSvc.findById(6);
		assertNotNull(range6);
		MountainRange range7 = mrSvc.findById(7);
		assertNotNull(range7);
		MountainRange range8 = mrSvc.findById(8);
		assertNotNull(range8);

		// Verify other ranges' parent is not range initially
		MountainRange range5Parent = range5.getParent();
		assertNotNull(range5Parent);
		assertNotEquals(range, range5Parent);
		MountainRange range6Parent = range6.getParent();
		assertNotNull(range6Parent);
		assertNotEquals(range, range6Parent);
		MountainRange range7Parent = range7.getParent();
		assertNotNull(range7Parent);
		assertNotEquals(range, range7Parent);
		MountainRange range8Parent = range8.getParent();
		assertNotNull(range8Parent);
		assertNotEquals(range, range8Parent);

		// Add other ranges to range's subranges
		range.addSubrange(range5);
		range.addSubrange(range6);
		range.addSubrange(range7);
		range.addSubrange(range8);

		// Verify locally
		assertTrue(range.getSubranges().contains(range5));
		assertEquals(range, range5.getParent());
		assertTrue(range.getSubranges().contains(range6));
		assertEquals(range, range6.getParent());
		assertTrue(range.getSubranges().contains(range7));
		assertEquals(range, range7.getParent());
		assertTrue(range.getSubranges().contains(range8));
		assertEquals(range, range8.getParent());

		// Update on DB
		MountainRange updatedRange = mrSvc.update(range);
		range = null;
		assertNotNull(updatedRange);

		// Verify range has subranges
		assertNotNull(updatedRange.getSubranges());
		assertEquals(numSubrangesToAdd, updatedRange.getSubranges().size());
		assertTrue(updatedRange.getSubranges().contains(range5));
		assertTrue(updatedRange.getSubranges().contains(range6));
		assertTrue(updatedRange.getSubranges().contains(range7));
		assertTrue(updatedRange.getSubranges().contains(range8));

		// Find other ranges in DB
		MountainRange updatedRange5 = mrSvc.findById(5);
		assertNotNull(updatedRange5);
		MountainRange updatedRange6 = mrSvc.findById(6);
		assertNotNull(updatedRange6);
		MountainRange updatedRange7 = mrSvc.findById(7);
		assertNotNull(updatedRange7);
		MountainRange updatedRange8 = mrSvc.findById(8);
		assertNotNull(updatedRange8);

		// Verify other ranges' parent is updatedRange
		assertEquals(updatedRange, updatedRange5.getParent());
		assertEquals(updatedRange, updatedRange6.getParent());
		assertEquals(updatedRange, updatedRange7.getParent());
		assertEquals(updatedRange, updatedRange8.getParent());

		// NOTE: updated list with @Cascade(CascadeType.MERGE) to make above this work

		// Revert range5's parent
		assertNotEquals(updatedRange, range5Parent);
		range5.setParent(range5Parent);

		// Remove range5 from updatedRange's subranges
		updatedRange.removeSubrange(range5);

		// NOTE: must remove and persist from both places. If not in list, no way to
		// update/merge/etc

		// Verify locally
		assertFalse(updatedRange.getSubranges().contains(range5));
		assertEquals(numSubrangesToAdd - 1, updatedRange.getSubranges().size());

		// Update range5 on DB
		MountainRange updatedRange52 = mrSvc.update(range5);
		assertNotNull(updatedRange52);
		range5 = null;

		// Update updatedRange on DB
		MountainRange updatedRange2 = mrSvc.update(updatedRange);
		assertNotNull(updatedRange2);
		updatedRange = null;

		// Verify changes to range5
		assertNotEquals(updatedRange, updatedRange52.getParent());
		assertNotEquals(range, updatedRange52.getParent());
		assertEquals(range5Parent, updatedRange52.getParent());

		// Verify changes to updatedRange2
		assertFalse(updatedRange2.getSubranges().contains(range5));
		assertTrue(updatedRange2.getSubranges().contains(range6));
		assertTrue(updatedRange2.getSubranges().contains(range7));
		assertTrue(updatedRange2.getSubranges().contains(range8));
		assertEquals(numSubrangesToAdd - 1, updatedRange2.getSubranges().size());

		// Find other ranges in DB
		MountainRange updatedRange62 = mrSvc.findById(6);
		assertNotNull(updatedRange62);
		updatedRange6 = null;
		MountainRange updatedRange72 = mrSvc.findById(7);
		assertNotNull(updatedRange72);
		updatedRange7 = null;
		MountainRange updatedRange82 = mrSvc.findById(8);
		assertNotNull(updatedRange82);
		updatedRange8 = null;

		// Verify no changes to other ranges
		assertEquals(updatedRange2, updatedRange62.getParent());
		assertEquals(updatedRange2, updatedRange72.getParent());
		assertEquals(updatedRange2, updatedRange82.getParent());

		// revert other ranges' parents
		updatedRange62.setParent(range6Parent);
		updatedRange72.setParent(range7Parent);
		updatedRange82.setParent(range8Parent);

		// Remove other ranges' from updatedRange's subranges
		updatedRange2.removeSubrange(range6);
		updatedRange2.removeSubrange(range7);
		updatedRange2.removeSubrange(range8);

		// Verify locally
		assertEquals(rangeSubrangeCount, updatedRange2.getSubranges().size());
		assertNotEquals(updatedRange2, range6.getParent());
		assertNotEquals(updatedRange2, range7.getParent());
		assertNotEquals(updatedRange2, range8.getParent());

		// Update other ranges' on DB
		MountainRange revertedRange6 = mrSvc.update(updatedRange62);
		assertNotNull(revertedRange6);
		updatedRange62 = null;
		MountainRange revertedRange7 = mrSvc.update(updatedRange72);
		assertNotNull(revertedRange7);
		updatedRange62 = null;
		MountainRange revertedRange8 = mrSvc.update(updatedRange82);
		assertNotNull(revertedRange8);
		updatedRange82 = null;

		// Update updatedRange2 on DB
		MountainRange revertedRange = mrSvc.update(updatedRange2);
		assertNotNull(revertedRange);
		updatedRange2 = null;

		// Verify changes to other ranges'
		assertEquals(range6Parent, revertedRange6.getParent());
		assertEquals(range7Parent, revertedRange7.getParent());
		assertEquals(range8Parent, revertedRange8.getParent());

		// Verify changes on revertedRange
		assertNotNull(revertedRange.getSubranges());
		assertEquals(rangeSubrangeCount, revertedRange.getSubranges().size());
		assertFalse(revertedRange.getSubranges().contains(revertedRange6));
		assertFalse(revertedRange.getSubranges().contains(revertedRange7));
		assertFalse(revertedRange.getSubranges().contains(revertedRange8));

		// Verify nothing added or removed
		assertEquals(numRanges, mrSvc.index().size());
	}

//	@Test
//	@DisplayName("MountainRangeService delete and restore with self-mappings")
//	void test_delete_self_mappings() {
//		// Settings
//		final int rangeCount = mrSvc.index().size();
//		
//		// Find MountainRanges and keep for restoring later;
//		final List<MountainRange> rangesBackup = mrSvc.index();
//		assertNotNull(rangesBackup);
//
//		// Find MountainRange
//		MountainRange range = mrSvc.findById(rangeId);
//		assertNotNull(range);
//		final MountainRange backup = range;
//		assertNotNull(backup);
//		assertEquals(rangeId, backup.getId());
//		
//		// Find parent and keep for restoring later
//		assertNotNull(range.getParent());
//		MountainRange parent = range.getParent();
//		
//		// Find subranges and keep for restoring later
//		assertNotNull(range.getSubranges());
//		Set<MountainRange> originalSubranges = range.getSubranges();
//		
//		// Delete range from DB
//		assertTrue(mrSvc.deleteById(rangeId));
//		
//		// Verify
//		assertNull(mrSvc.findById(rangeId));
//		assertFalse(mrSvc.index().contains(range));
//		
//		// Check all ranges in DB
//		for (MountainRange r : mrSvc.index()) {
//			
//			// Verify no ranges have deleted range as parent
//			assertNotEquals(r.getParent(), range);
//			
//			// Verify no ranges have deleted range as subrange
//			assertFalse(r.getSubranges().contains(range));
//		}
//		
//		// Persist backup to DB
//		MountainRange restored = mrSvc.create(backup);
//		assertNotNull(restored);
//		assertNotEquals(rangeId, restored.getId());
//		
//		//restored ID does not match original
//		restored.setId(rangeId);
//		MountainRange updated = mrSvc.update(restored);
//		assertNotNull(updated);
//		assertNotEquals(rangeId, updated.getId());
//		
//		// NOTE: on deleting, references cascade to NULL automatically through DB.
//		// Should not allow anyone to delete a MountainRange but it is possible
//		// Commenting out this test so it doesn't interfere with DB ids
//		
//	}

}
