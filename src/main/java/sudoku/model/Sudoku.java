package sudoku.model;

import sudoku.model.Cell;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {
	public final Cell[][] board;
	public final List<List<Integer>> candidates;

	public Sudoku() {
		board = new Cell[9][9];
		candidates = new ArrayList<>();

		for (int i=0;i<9*9;i++) {
			candidates.add(new ArrayList<>());
		}
	}

	public Sudoku(int[][] board) {
		this();

		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				this.board[i][j]=new Cell(board[i][j]);
			}
		}
	}

	public String toString() {
		StringBuilder s=new StringBuilder();

		for (int i=0;i<9;i++) {
			for (int j=0;j<8;j++) {
				s.append(board[i][j]);
				s.append(" , ");
			}
			s.append(board[i][8]);
			s.append("\n");
		}

		return s.toString();
	}

	public int[][] getBoardAsInts() {
		int[][] result = new int[9][9];
		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				result[i][j]=board[i][j].getValue();
			}
		}
		return result;
	}

	public Cell getCellAt(int x, int y) {
		return board[x][y];
	}

	public Cell getCell(int i) {
		int x, y;
		x=i % 9 / 3 + (i / 27)*3;
		y=i%3 + ((i/9)*3) % 9;
		return board[x][y];
	}
}

/*
 0  1  2  9 10 11 18 19 20
 3  4  5 12 13 14 21 22 23
 6  7  8 15 16 17 24 25 26
27 28 29 36 37 38 45 46 47
30 31 32 39 40 41 48 49 50
33 34 35 42 43 44 51 52 53
54 55 56 63 64 65 72 73 74
57 58 59 66 67 68 75 76 77
60 61 62 69 70 71 78 79 80
 */


