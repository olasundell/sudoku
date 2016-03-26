package sudoku;

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
			candidates.add(new ArrayList<Integer>());
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

	public int[][] trySolve() {
		findCandidates();
		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				if (board[i][j].isUndecided()) {
					calculateValueForSquare(i,j);
				}
			}

		}
		return getBoardAsInts();
	}

	public void calculateCandidates() {
		ArrayList<ArrayList<Integer>> rowCandidates=new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> colCandidates=new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> blockCandidates=new ArrayList<ArrayList<Integer>>();
		for (int i=0;i<9;i++) {
			rowCandidates.add(findRowCandidates(i));
			colCandidates.add(findColCandidates(i));
			blockCandidates.add(findBlockCandidates(i));
		}

		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				if (board[i][j].getValue()==0) {
					board[i][j].clearCandidates();
					for (int k=1;k<=9;k++) {
						if (rowCandidates.get(i).contains(k) &&
								colCandidates.get(j).contains(k) &&
								blockCandidates.get((i/3)*3 + (j/3)).contains(k)) {
							board[i][j].addCandidate(k);
						}
					}
				}
			}
		}
	}

	private List<List<Integer>> findCandidates() {
		List<List<Integer>> candidates=new ArrayList<>();

		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				candidates.add(board[i][j].getCandidates());
			}
		}
		return candidates;
	}

	private void calculateValueForSquare(int i, int j) {
	}

	protected ArrayList<Integer> findBlockCandidates(int block) {
		ArrayList<Integer> arr=new ArrayList<Integer>();

		for (int i=1;i<=9;i++) {
			arr.add(i);
		}

		int row;
		int col;

		for (int i=0;i<9;i++) {
			row=(block / 3)*3 + i / 3;
			col=(block % 3)*3 + i % 3;
			if (board[row][col].getValue()>0) {
				arr.remove(Integer.valueOf(board[row][col].getValue()));
			}
		}

		return arr;
	}

	protected ArrayList<Integer> findColCandidates(int col) {
		ArrayList<Integer> arr=new ArrayList<Integer>();

		for (int i=1;i<=9;i++) {
			arr.add(i);
		}

		for (int i=0;i<9;i++) {
			if (board[i][col].getValue()>0) {
				arr.remove(Integer.valueOf(board[i][col].getValue()));
			}
		}

		return arr;
	}

	protected ArrayList<Integer> findRowCandidates(int row) {
		ArrayList<Integer> arr=new ArrayList<Integer>();

		for (int i=1;i<=9;i++) {
			arr.add(i);
		}

		for (int i=0;i<9;i++) {
			if (board[row][i].getValue()>0) {
				arr.remove(Integer.valueOf(board[row][i].getValue()));
			}
		}

		return arr;
	}

	public String toString() {
		StringBuffer s=new StringBuffer();

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


