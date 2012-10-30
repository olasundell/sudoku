package sudoku;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: Feb 1, 2010
 * Time: 4:15:19 PM
 * To change this template use File | Settings | File Templates.
 */


public class RankSolver extends Solver {

	protected int blockIterate(Sudoku sudoku, int i, int j) {
		Cell currentCell=sudoku.board[i][j];
		ArrayList<Integer> blockCandidates = SudokuUtil.getBlockCandidates(sudoku, i, j);

		for (int k=0;k<currentCell.candidates.size();k++) {
			Integer c=currentCell.candidates.get(k);
			if (!blockCandidates.contains(c)) {
				return c;
			}
		}

		return 0;
	}

	protected int rowIterate(Sudoku sudoku, int i, int j) {
		Cell currentCell=sudoku.board[i][j];
		ArrayList<Integer> rowCandidates = SudokuUtil.getRowCandidates(sudoku, i, j);

		for (int k=0;k<currentCell.candidates.size();k++) {
			Integer c=currentCell.candidates.get(k);
			if (!rowCandidates.contains(c)) {
				return c;
			}
		}

		return 0;
	}

	protected int colIterate(Sudoku sudoku, int i, int j) {
		Cell currentCell=sudoku.board[i][j];
		ArrayList<Integer> colCandidates = SudokuUtil.getColCandidates(sudoku, i, j);

		for (int k=0;k<currentCell.candidates.size();k++) {
			Integer c=currentCell.candidates.get(k);
			if (!colCandidates.contains(c)) {
				return c;
			}
		}
		
		return 0;
	}

	public boolean iterate(Sudoku sudoku) {
		int c;
		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				if (sudoku.board[i][j].isUndecided()) {
					c=blockIterate(sudoku,i,j);
					
					if (c == 0) {
						c=rowIterate(sudoku,i,j);
					}
					
					if (c == 0) {
						c=colIterate(sudoku,i,j);
					}
					
					if (c > 0) {
						setSolution(c, sudoku, j, i);
						return true;
					}
				}
			}
		}
		
/*		Cell currentCell;
		ArrayList<Integer> rowCandidates=new ArrayList<Integer>();
		ArrayList<Integer> colCandidates=new ArrayList<Integer>();
		ArrayList<Integer> blockCandidates=new ArrayList<Integer>();

		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				currentCell=sudoku.board[i][j];

				if (!currentCell.isUndecided()) {
					// nothing to see, move along
					continue;
				}
				rowCandidates.clear();
				colCandidates.clear();
				blockCandidates.clear();

				for (int k=0;k<9;k++) {
					int x,y;

					x = i - i % 3 + k % 3;
					y = j - j % 3 + k / 3;

					if (x!=i || y!=j) {
						blockCandidates.addAll(sudoku.board[x][y].candidates);
					}

					if (k!=j) { // check row
						rowCandidates.addAll(sudoku.board[i][k].candidates);
					}

					if (k!=i) { // check col
						colCandidates.addAll(sudoku.board[k][j].candidates);
					}

				}

				for (int k=0;k<currentCell.candidates.size();k++) {
					Integer c=currentCell.candidates.get(k);
					if (!blockCandidates.contains(c) || !rowCandidates.contains(c) || !colCandidates.contains(c)) {
						setSolution(c, sudoku, i, j);
						return true;
					}
				}
			}
		}
		*/
		System.err.println("RankSolver couldn't find a candidate good enough!");
		
		return false;
	}
}
