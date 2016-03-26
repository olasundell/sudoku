package sudoku.model;

import org.testng.annotations.BeforeMethod;
import sudoku.util.SudokuUtil;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertArrayEquals;


public class SudokuTest {

	Sudoku solver;

	private final Integer [][] colCandidates;

	private final Integer [][] rowCandidates;

	private final Integer [][] blockCandidates;

	public SudokuTest() {
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

	@BeforeMethod
	public void setup() {
		solver=new Sudoku(SudokuUtil.BOARD);
	}

	@org.testng.annotations.Test
	public void testCandidates() {
		ArrayList<ArrayList<Integer>> arr= new ArrayList<>();
		ArrayList<ArrayList<Integer>> rows= new ArrayList<>();
		ArrayList<ArrayList<Integer>> cols= new ArrayList<>();
		ArrayList<ArrayList<Integer>> blocks= new ArrayList<>();

		for (int i=0;i<9;i++) {
			ArrayList<Integer> r= new ArrayList<>();
			Collections.addAll(r, rowCandidates[i]);
			rows.add(r);

			ArrayList<Integer> c= new ArrayList<>();
			Collections.addAll(c, colCandidates[i]);
			cols.add(c);

			ArrayList<Integer> b= new ArrayList<>();
			Collections.addAll(b, colCandidates[i]);
			blocks.add(b);
		}

		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				ArrayList<Integer> a= new ArrayList<>();
				arr.add(a);
				if (SudokuUtil.BOARD[i][j]==0) {
					for (int k=1;k<=9;k++) {
						if (rows.get(i).contains(k) &&
								cols.get(j).contains(k) &&
								blocks.get((i/3)*3 + (j%3)).contains(k)) {
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
			ArrayList<Integer> results=solver.findColCandidates(i);
			assertArrayEquals(""+i,
					colCandidates[i],
					results.toArray(new Integer[results.size()]));
		}
	}

	@org.testng.annotations.Test
	public void testRowCandidates() {
		for (int i=0;i<9;i++) {
			ArrayList<Integer> results=solver.findRowCandidates(i);
			assertArrayEquals(""+i,
					rowCandidates[i],
					results.toArray(new Integer[results.size()]));
		}
	}

	@org.testng.annotations.Test
	public void testBlockCandidates() {
		for (int i=0;i<9;i++) {
			ArrayList<Integer> results=solver.findBlockCandidates(i);
			assertArrayEquals(""+i,
					blockCandidates[i],
					results.toArray(new Integer[results.size()]));
		}
	}

//	@Test
	@org.testng.annotations.Test
	public void testSolve() {
//		int[][] result=solver.trySolve();
//		assertArrayEquals(SOLUTION,result);
	}
}
