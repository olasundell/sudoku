package sudoku.factory;

import sudoku.model.Sudoku;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class SudokuFactoryTest {

	@org.testng.annotations.Test
	public void testCreateSudoku() throws SudokuCreationException {
		SudokuFactory factory;
		
		Sudoku sudoku;
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
//		sudoku.BOARD[0][0].setValue(1);
//		assertTrue(SudokuFactory.getInstance().isCorrect(sudoku));
//		sudoku.BOARD[0][1].setValue(1);
//		assertFalse(SudokuFactory.getInstance().isCorrect(sudoku));
//		sudoku.BOARD[0][1].setValue(2);
//		assertTrue(SudokuFactory.getInstance().isCorrect(sudoku));
//		sudoku.BOARD[1][0].setValue(2);
//		assertFalse(SudokuFactory.getInstance().isCorrect(sudoku));
//		sudoku.BOARD[1][0].setValue(3);
//		assertTrue(SudokuFactory.getInstance().isCorrect(sudoku));
//	}
}
