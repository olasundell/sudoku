package sudoku;

import java.util.ArrayList;

public class SudokuUtil {
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
		ArrayList<Integer> blockCandidates=new ArrayList<Integer>();

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
		ArrayList<Integer> colCandidates=new ArrayList<Integer>();

		for (int k=0;k<9;k++) {
			if (k!=i) { // check col
				colCandidates.addAll(sudoku.board[k][j].candidates);
			}
		}

		return colCandidates;
	}
	
	public static ArrayList<Integer> getRowCandidates(Sudoku sudoku, int i, int j) {
		ArrayList<Integer> rowCandidates=new ArrayList<Integer>();

		for (int k=0;k<9;k++) {
			if (k!=j) { // check row
				rowCandidates.addAll(sudoku.board[i][k].candidates);
			}
		}

		return rowCandidates;
	}

}
