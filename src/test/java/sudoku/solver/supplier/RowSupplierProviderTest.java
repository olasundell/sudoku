package sudoku.solver.supplier;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sudoku.factory.SudokuCreationException;
import sudoku.model.Sudoku;
import sudoku.util.SudokuUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO write documentation
 */
public class RowSupplierProviderTest {
	Sudoku sudoku;

	@BeforeMethod
	public void createSudoku() throws SudokuCreationException {
		sudoku = new Sudoku(SudokuUtil.VERY_HARD_BOARD);
	}

	@Test
	public void shouldProvideRowCandidates() {
		Set<Integer> result = new RowSupplierProvider().createCandidates(sudoku, 0, 0);
		Assert.assertNotNull(result);
		Assert.assertNotEquals(0, result.size(), "Row supplier should provide non-empty result");
		Set<Integer> expected = new HashSet<>(Arrays.asList(2, 3, 6, 7, 8, 9));

		Assert.assertEquals(result, expected, "Row candidates aren't what was expected");
	}
}