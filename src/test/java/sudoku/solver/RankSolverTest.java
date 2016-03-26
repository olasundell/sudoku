package sudoku.solver;

import org.testng.annotations.BeforeMethod;
import sudoku.model.Sudoku;
import sudoku.util.SudokuUtil;

import static org.junit.Assert.assertEquals;

public class RankSolverTest {
	RankSolver solver;
	
	@BeforeMethod
	public void setUp() throws Exception {
		solver=new RankSolver();
	}

	@org.testng.annotations.Test
	public void testBlockIterate() {
		Sudoku sudoku = new Sudoku(SudokuUtil.VERY_HARD_BOARD);
		sudoku.calculateCandidates();
		assertEquals(4,solver.blockIterate(sudoku, 1, 0));
		assertEquals(1,solver.blockIterate(sudoku, 2, 8));
		assertEquals(9,solver.blockIterate(sudoku, 5, 1));
		assertEquals(0,solver.blockIterate(sudoku, 5, 3));
	}

	@org.testng.annotations.Test
	public void testRowIterate() {
		Sudoku sudoku = new Sudoku(SudokuUtil.VERY_HARD_BOARD);
		sudoku.calculateCandidates();
		assertEquals(4,solver.rowIterate(sudoku, 1, 0));
		assertEquals(1,solver.rowIterate(sudoku, 2, 8));
		assertEquals(4,solver.rowIterate(sudoku, 8, 8));
		assertEquals(0,solver.rowIterate(sudoku, 7, 5));
	}

	@org.testng.annotations.Test(enabled = false)
	public void testColIterate() {
		Sudoku sudoku = new Sudoku(SudokuUtil.VERY_HARD_BOARD);
		assertEquals(9,solver.colIterate(sudoku, 0, 3));
	}

}
