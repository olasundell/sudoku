package sudoku.factory;

import org.testng.Assert;
import sudoku.model.Sudoku;

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
				Assert.assertNotNull(sudoku, Integer.toString(i));
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			Assert.assertFalse( true, "ArrayIndexOutOfBoundsException occurred with seed "+i );
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
