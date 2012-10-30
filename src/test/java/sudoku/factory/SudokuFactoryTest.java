package sudoku.factory;

import static org.junit.Assert.*;


import org.apache.log4j.Logger;
import org.junit.Test;

import sudoku.Sudoku;
import sudoku.factory.SudokuFactory;

public class SudokuFactoryTest {

	@Test
	public void testCreateSudoku() throws SudokuCreationException {
		SudokuFactory factory;
		
		Sudoku sudoku = null;
		int i=0;
		
		try {
			for (i=0;i<100;i++) {
				factory = new SudokuFactory(i);
				sudoku = factory.createSudoku();
				assertNotNull(Integer.toString(i),sudoku);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			assertFalse("ArrayIndexOutOfBoundsException occurred with seed "+i,true);
		}
	}
//
//	@Test
//	public void testIsCorrect() {
//		Sudoku sudoku = new Sudoku(new int[9][9]);
//		
//		assertTrue(SudokuFactory.getInstance().isCorrect(sudoku));
//		sudoku.board[0][0].setValue(1); 
//		assertTrue(SudokuFactory.getInstance().isCorrect(sudoku));
//		sudoku.board[0][1].setValue(1); 
//		assertFalse(SudokuFactory.getInstance().isCorrect(sudoku));
//		sudoku.board[0][1].setValue(2); 
//		assertTrue(SudokuFactory.getInstance().isCorrect(sudoku));
//		sudoku.board[1][0].setValue(2); 
//		assertFalse(SudokuFactory.getInstance().isCorrect(sudoku));
//		sudoku.board[1][0].setValue(3); 
//		assertTrue(SudokuFactory.getInstance().isCorrect(sudoku));
//	}
}
