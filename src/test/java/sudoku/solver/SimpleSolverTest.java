package sudoku.solver;

import org.junit.experimental.theories.suppliers.TestedOn;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sudoku.model.Sudoku;
import sudoku.util.SudokuUtil;


/**
 * TODO write documentation
 */
public class SimpleSolverTest {
	SimpleSolver solver;

	@BeforeMethod
	public void setupSolver() {
		this.solver = new SimpleSolver();
	}

	// TODO re-enable this test
	@Test(enabled = false)
	public void shouldSolveSomething() {
		Assert.assertTrue(solver.iterate(new Sudoku(SudokuUtil.BOARD)));
	}
}