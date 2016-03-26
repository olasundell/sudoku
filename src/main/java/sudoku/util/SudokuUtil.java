package sudoku.util;

import sudoku.model.Sudoku;
import sudoku.model.Cell;

import java.util.ArrayList;

public class SudokuUtil {
	public static final int[][] SOLUTION = {
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
	public static final int[][] BOARD = {
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
	public static final int[][] VERY_HARD_BOARD = {
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
	public static final int[][] VERY_HARD_BOARD_SOLUTION = {
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

	public static Cell[] getBlock(Sudoku sudoku, int num) {
		return getBlock(sudoku, 3* (num % 3), 3 * (num / 3));
	}
	
	public static Cell[] getBlock(Sudoku sudoku, int i, int j) {
		Cell[] block = new Cell[9];
		
		for (int k=0;k<9;k++) {
			int x,y;

			y = i - i % 3 + k % 3;
			x = j - j % 3 + k / 3;
			block[k] = sudoku.board[y][x];
		}		
		return block;
	}
	
	public static ArrayList<Integer> getBlockCandidates(Sudoku sudoku, int i, int j) {
		ArrayList<Integer> blockCandidates= new ArrayList<>();

		for (int k=0;k<9;k++) {
			int x,y;

			y = i - i % 3 + k % 3;
			x = j - j % 3 + k / 3;

			if ((x!=j || y!=i) && sudoku.board[y][x].isUndecided()) {
				blockCandidates.addAll(sudoku.board[y][x].candidates);
			}
		}
		
		return blockCandidates;
	}

	public static ArrayList<Integer> getColCandidates(Sudoku sudoku, int i, int j) {
		ArrayList<Integer> colCandidates= new ArrayList<>();

		for (int k=0;k<9;k++) {
			if (k!=i) { // check col
				colCandidates.addAll(sudoku.board[k][j].candidates);
			}
		}

		return colCandidates;
	}
	
	public static ArrayList<Integer> getRowCandidates(Sudoku sudoku, int i, int j) {
		ArrayList<Integer> rowCandidates= new ArrayList<>();

		for (int k=0;k<9;k++) {
			if (k!=j) { // check row
				rowCandidates.addAll(sudoku.board[i][k].candidates);
			}
		}

		return rowCandidates;
	}

}
