package sudoku.solver.supplier;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sudoku.factory.SudokuCreationException;
import sudoku.model.Candidates;
import sudoku.model.Coordinate;
import sudoku.model.Sudoku;
import sudoku.util.SudokuUtil;

import java.awt.*;
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

	@Test
	public void shouldProvideBlockStuff() {
		Candidates result = new BlockSupplierProvider().createCandidates(sudoku, 0, 0);

		Assert.assertNotNull(result);
		Assert.assertNotEquals(0, result.getCandidates().size(), "Block supplier should provide non-empty result");

		Set<Integer> expectedCandidates = new HashSet<>(Arrays.asList(2, 3, 4, 5, 6, 8));

//		0,0,1
//		0,0,9
//      0,7,0
		Set<Coordinate> expectedPoints = new HashSet<>(Arrays.asList(
				Coordinate.builder().x(0).y(0).build(),
				Coordinate.builder().x(1).y(0).build(),
				Coordinate.builder().x(0).y(1).build(),
				Coordinate.builder().x(1).y(1).build(),
				Coordinate.builder().x(0).y(2).build(),
				Coordinate.builder().x(2).y(2).build()));


		Assert.assertEquals(result.getCandidates(), expectedCandidates, "Block candidates aren't what was expected");
		Assert.assertEquals(result.getCells(), expectedPoints);
	}

}