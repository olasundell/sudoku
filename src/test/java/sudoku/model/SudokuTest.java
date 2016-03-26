package sudoku.model;

import org.testng.annotations.BeforeMethod;
import sudoku.model.Sudoku;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;


public class SudokuTest {
	int[][] solution = {
			{5,3,4,6,7,8,9,1,2},
			{6,7,2,1,9,5,3,4,8},
			{1,9,8,3,4,2,5,6,7},
			{8,5,9,7,6,1,4,2,3},
			{4,2,6,8,5,3,7,9,1},
			{7,1,3,9,2,4,8,5,6},
			{9,6,1,5,3,7,2,8,4},
			{2,8,7,4,1,9,6,3,5},
			{3,4,5,2,8,6,1,7,9}
	};

	public static int[][] board = {
			{5,3,0,0,7,0,0,0,0},
			{6,0,0,1,9,5,0,0,0},
			{0,9,8,0,0,0,0,6,0},
			{8,0,0,0,6,0,0,0,3},
			{4,0,0,8,0,3,0,0,1},
			{7,0,0,0,2,0,0,0,6},
			{0,6,0,0,0,0,2,8,0},
			{0,0,0,4,1,9,0,0,5},
			{0,0,0,0,8,0,0,7,9}
	};

	public static int[][] veryHardBoard = {
			{0,0,1,0,0,4,0,5,0},
			{0,0,9,0,0,1,3,0,0},
			{0,7,0,5,0,8,0,4,0},
			{0,0,0,0,0,0,1,0,9},
			{0,0,0,8,0,9,0,0,0},
			{2,0,8,0,0,0,0,0,0},
			{0,4,0,1,0,2,0,9,0},
			{0,0,5,4,0,0,7,0,0},
			{0,3,0,7,0,0,5,0,0}
	};
	
	public static int[][] veryHardBoardSolution = {
		{},
		{},
		{},
		{},
		{},
		{},
		{},
		{},
		{}
	};

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
		solver=new Sudoku(board);
	}

	@org.testng.annotations.Test
	public void testCandidates() {
		ArrayList<ArrayList<Integer>> arr=new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> rows=new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> cols=new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> blocks=new ArrayList<ArrayList<Integer>>();

		for (int i=0;i<9;i++) {
			ArrayList<Integer> r=new ArrayList<Integer>();
			for (Integer j:rowCandidates[i]) {
				r.add(j);
			}
			rows.add(r);

			ArrayList<Integer> c=new ArrayList<Integer>();
			for (Integer j:colCandidates[i]) {
				c.add(j);
			}
			cols.add(c);

			ArrayList<Integer> b=new ArrayList<Integer>();
			for (Integer j:colCandidates[i]) {
				b.add(j);
			}
			blocks.add(b);
		}

		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				ArrayList<Integer> a=new ArrayList<Integer>();
				arr.add(a);
				if (board[i][j]==0) {
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
//		assertArrayEquals(solution,result);
	}
}
