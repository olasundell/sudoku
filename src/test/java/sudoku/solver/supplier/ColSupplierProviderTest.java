package sudoku.solver.supplier;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sudoku.factory.SudokuCreationException;
import sudoku.model.Candidates;
import sudoku.model.Sudoku;
import sudoku.util.SudokuUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO write documentation
 */
public class ColSupplierProviderTest {
	Sudoku sudoku;

	@BeforeMethod
	public void createSudoku() throws SudokuCreationException {
		sudoku = new Sudoku(SudokuUtil.VERY_HARD_BOARD);
	}

	@Test
	public void shouldProvideColCandidates() {
		Candidates result = new ColSupplierProvider().createCandidates(sudoku, 0, 0);
		Assert.assertNotNull(result.getCandidates());
		Assert.assertNotEquals(0, result.getCandidates().size(), "Row supplier should provide non-empty result");
		Set<Integer> expected = new HashSet<>(Arrays.asList(1, 3, 4, 5, 6, 7, 8, 9));

		Assert.assertEquals(result.getCandidates(), expected, "Row candidates aren't what was expected");
	}
}