package sudoku.solver.supplier;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import sudoku.factory.SudokuCreationException;
import sudoku.factory.SudokuFactory;
import sudoku.model.Sudoku;

import java.util.Set;

/**
 * TODO write documentation
 */
public class BlockSupplierProviderTest {
	Sudoku sudoku;

	@BeforeMethod
	public void createSudoku() throws SudokuCreationException {
		sudoku = new SudokuFactory(0).createSudoku();
	}

	@org.testng.annotations.Test
	public void shouldProvideBlockStuff() {
		Set<Integer> result = new BlockSupplierProvider().createCandidates(sudoku, 0, 0);
		Assert.assertNotNull(result);
	}

}