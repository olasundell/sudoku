package sudoku.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import sudoku.factory.SudokuFactory.State;

public class LevelTest {
	/*
	 *  0  1  2| 3  4  5| 6  7  8
	 *  9 10 11|12 13 14|15 16 17
	 * 18 19 20|21 22 23|24 25 26
	 * --------+--------+--------
	 * 27 28 29|30 31 32|33 34 35
	 * 36 37 38|39 40 41|42 43 44
	 * 45 46 47|48 49 50|51 52 53
	 * --------+--------+--------
	 * 54 55 56|57 58 59|60 61 62
	 * 63 64 65|66 67 68|69 70 71
	 * 72 73 74|75 76 77|78 79 80
	 */
	@Test
	public void testFindBlockPos() {
		Level level = new Level(0);
		
		assertEquals(1,level.findBlockPos(0, 1));
		assertEquals(0,level.findBlockPos(10, 0));
		assertEquals(27,level.findBlockPos(29, 0));
		assertEquals(37,level.findBlockPos(29, 4));
		assertEquals(75,level.findBlockPos(76, 6));
		assertEquals(15,level.findBlockPos(26, 3));
	}
	
	
	@Test
	public void testSetValue() {
		Level level = new Level(1);
		assertEquals(81,level.freeSquares.size());
		level.setValue(0, State.TAKEN);
		
		// row
		assertFalse(level.setValue(0, State.TAKEN));
		assertFalse(level.setValue(1, State.TAKEN));
		assertFalse(level.setValue(2, State.TAKEN));
		
		// col
		assertFalse(level.setValue(9, State.TAKEN));
		assertFalse(level.setValue(18, State.TAKEN));

		// block
		assertFalse(level.setValue(10, State.TAKEN));
		
		assertTrue(level.setValue(40, State.TAKEN));
		assertFalse(level.setValue(50, State.TAKEN));
//		System.out.println(level);
	}
	
	@Test
	public void testAllocateSquare() {
		Random randomiser = new Random();
		Level level = new Level(1);
		
		for (int i=0;i<9;i++) {
			level.clear();
			randomiser.setSeed(i);
			
			for (int j=0;j<9;j++) {
				level.allocateFreeSquare(randomiser.nextInt(level.noOfFreeSquares()));
			}
			assertEquals(0, level.noOfFreeSquares());
		}
		Level level2 = new Level(2);
		for (Integer i: level.takenSquares) {
			level2.setValue(i, State.ILLEGAL);
		}
		
		assertEquals(81-9, level2.noOfFreeSquares());
		
		int tries = 10;
		
		for (int j=0;j<9;j++) {
			if (!level2.allocateFreeSquare(randomiser.nextInt(level2.noOfFreeSquares()))) {
				j--;
				tries--;
				if (tries == 0) { // don't loop forever
					assertTrue("We ran out of tries to allocate a free square",false);
					break;
				}
			}
		}
		assertEquals(0, level2.noOfFreeSquares());
	}
}
