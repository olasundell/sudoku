package sudoku.solver.supplier;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import sudoku.factory.SudokuCreationException;
import sudoku.model.Sudoku;
import sudoku.util.SudokuUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO write documentation
 */
public class BlockSupplierProviderTest {
	Sudoku sudoku;

	@BeforeMethod
	public void createSudoku() throws SudokuCreationException {
		sudoku = new Sudoku(SudokuUtil.VERY_HARD_BOARD);
	}

	@org.testng.annotations.Test
	public void shouldProvideBlockStuff() {
		Set<Integer> result = new BlockSupplierProvider().createCandidates(sudoku, 0, 0);
		Assert.assertNotNull(result);
		Assert.assertNotEquals(0, result.size(), "Block supplier should provide non-empty result");
		Set<Integer> expected = new HashSet<>(Arrays.asList(2, 3, 4, 5, 6, 8));

		Assert.assertEquals(result, expected, "Block candidates aren't what was expected");
	}

}