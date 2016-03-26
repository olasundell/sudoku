package sudoku.solver;

import org.testng.annotations.BeforeClass;
import sudoku.model.Sudoku;
import sudoku.util.SudokuUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

/**
 * TODO write documentation
 */
public class CandidateCalculatorTest {
	CandidateCalculator solver = new CandidateCalculator();

	private Integer [][] colCandidates;

	private Integer [][] rowCandidates;

	private Integer [][] blockCandidates;
	private Sudoku sudoku;

	@BeforeClass
	public void setupCandidates() {
		sudoku = new Sudoku(SudokuUtil.BOARD);

		colCandidates = new Integer[][] {
				{1,2,3,9},
				{1,2,4,5,7,8},
				{1,2,3,4,5,6,7,9},
				{2,3,5,6,7,9},
				{3,4,5},
				{1,2,4,6,7,8},
				{1,3,4,5,6,7,8,9},
				{1,2,3,4,5,9},
				{2,4,7,8}
		};
		rowCandidates = new Integer[][] {
				{1,2,4,6,8,9},
				{2,3,4,7,8},
				{1,2,3,4,5,7},
				{1,2,4,5,7,9},
				{2,5,6,7,9},
				{1,3,4,5,8,9},
				{1,3,4,5,7,9},
				{2,3,6,7,8},
				{1,2,3,4,5,6}
		};
		blockCandidates = new Integer[][] {
				{1,2,4,7},
				{2,3,4,6,8},
				{1,2,3,4,5,7,8,9},
				{1,2,3,5,6,9},
				{1,4,5,7,9},
				{2,4,5,7,8,9},
				{1,2,3,4,5,7,8,9},
				{2,3,5,6,7},
				{1,3,4,6}
		};
	}

	@org.testng.annotations.Test
	public void testCandidates() {
		List<List<Integer>> arr = new ArrayList<>();
		List<List<Integer>> rows = new ArrayList<>();
		List<List<Integer>> cols = new ArrayList<>();
		List<List<Integer>> blocks = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			ArrayList<Integer> r = new ArrayList<>();
			Collections.addAll(r, rowCandidates[i]);
			rows.add(r);

			ArrayList<Integer> c = new ArrayList<>();
			Collections.addAll(c, colCandidates[i]);
			cols.add(c);

			ArrayList<Integer> b = new ArrayList<>();
			Collections.addAll(b, colCandidates[i]);
			blocks.add(b);
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				ArrayList<Integer> a = new ArrayList<>();
				arr.add(a);
				if (SudokuUtil.BOARD[i][j] == 0) {
					for (int k = 1; k <= 9; k++) {
						if (rows.get(i).contains(k) &&
								cols.get(j).contains(k) &&
								blocks.get((i / 3) * 3 + (j % 3)).contains(k)) {
							a.add(k);
						}
					}
				}
			}
		}
	}

	@org.testng.annotations.Test
	public void testColCandidates() {
		for (int i=0;i<9;i++) {
			List<Integer> results = solver.findColCandidates(sudoku, i);
			assertArrayEquals(""+i,
					colCandidates[i],
					results.toArray(new Integer[results.size()]));
		}
	}

	@org.testng.annotations.Test
	public void testRowCandidates() {
		for (int i=0;i<9;i++) {
			List<Integer> results=solver.findRowCandidates(sudoku, i);
			assertArrayEquals(""+i,
					rowCandidates[i],
					results.toArray(new Integer[results.size()]));
		}
	}

	@org.testng.annotations.Test
	public void testBlockCandidates() {
		for (int i=0;i<9;i++) {
			List<Integer> results=solver.findBlockCandidates(sudoku, i);
			assertArrayEquals(""+i,
					blockCandidates[i],
					results.toArray(new Integer[results.size()]));
		}
	}


}