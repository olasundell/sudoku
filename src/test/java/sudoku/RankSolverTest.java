package sudoku;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RankSolverTest {
	RankSolver solver;
	
	@Before
	public void setUp() throws Exception {
		solver=new RankSolver();
	}

	@Test
	public void testBlockIterate() {
		Sudoku sudoku = new Sudoku(SudokuTest.veryHardBoard);
		sudoku.calculateCandidates();
		assertEquals(4,solver.blockIterate(sudoku, 1, 0));
		assertEquals(1,solver.blockIterate(sudoku, 2, 8));
		assertEquals(9,solver.blockIterate(sudoku, 5, 1));
		assertEquals(0,solver.blockIterate(sudoku, 5, 3));
	}

	@Test
	public void testRowIterate() {
		Sudoku sudoku = new Sudoku(SudokuTest.veryHardBoard);
		sudoku.calculateCandidates();
		assertEquals(4,solver.rowIterate(sudoku, 1, 0));
		assertEquals(1,solver.rowIterate(sudoku, 2, 8));
		assertEquals(4,solver.rowIterate(sudoku, 8, 8));
		assertEquals(0,solver.rowIterate(sudoku, 7, 5));
	}

	//@Test
	public void testColIterate() {
		Sudoku sudoku = new Sudoku(SudokuTest.veryHardBoard);
		assertEquals(9,solver.colIterate(sudoku, 0, 3));
	}

}
