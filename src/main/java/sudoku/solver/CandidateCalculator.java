package sudoku.solver;

import sudoku.model.Sudoku;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO write documentation
 */
class CandidateCalculator {
	void calculateCandidates(Sudoku sudoku) {
		List<List<Integer>> rowCandidates= new ArrayList<>();
		List<List<Integer>> colCandidates= new ArrayList<>();
		List<List<Integer>> blockCandidates= new ArrayList<>();
		for (int i=0;i<9;i++) {
			rowCandidates.add(findRowCandidates(sudoku, i));
			colCandidates.add(findColCandidates(sudoku, i));
			blockCandidates.add(findBlockCandidates(sudoku, i));
		}

		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				if (sudoku.board[i][j].getValue()==0) {
					sudoku.board[i][j].clearCandidates();
					for (int k=1;k<=9;k++) {
						if (rowCandidates.get(i).contains(k) &&
								colCandidates.get(j).contains(k) &&
								blockCandidates.get((i/3)*3 + (j/3)).contains(k)) {
							sudoku.board[i][j].addCandidate(k);
						}
					}
				}
			}
		}
	}

//	private List<List<Integer>> findCandidates() {
//		List<List<Integer>> candidates=new ArrayList<>();
//
//		for (int i=0;i<9;i++) {
//			for (int j=0;j<9;j++) {
//				candidates.add(sudoku.board[i][j].getCandidates());
//			}
//		}
//		return candidates;
//	}

	protected List<Integer> findBlockCandidates(Sudoku sudoku, int block) {
		List<Integer> arr= new ArrayList<>();

		for (int i=1;i<=9;i++) {
			arr.add(i);
		}

		int row;
		int col;

		for (int i=0;i<9;i++) {
			row=(block / 3)*3 + i / 3;
			col=(block % 3)*3 + i % 3;
			if (sudoku.board[row][col].getValue()>0) {
				arr.remove(Integer.valueOf(sudoku.board[row][col].getValue()));
			}
		}

		return arr;
	}

	protected List<Integer> findColCandidates(Sudoku sudoku, int col) {
		List<Integer> arr= new ArrayList<>();

		for (int i=1;i<=9;i++) {
			arr.add(i);
		}

		for (int i=0;i<9;i++) {
			if (sudoku.board[i][col].getValue()>0) {
				arr.remove(Integer.valueOf(sudoku.board[i][col].getValue()));
			}
		}

		return arr;
	}

	protected List<Integer> findRowCandidates(Sudoku sudoku, int row) {
		List<Integer> arr= new ArrayList<>();

		for (int i=1;i<=9;i++) {
			arr.add(i);
		}

		for (int i=0;i<9;i++) {
			if (sudoku.board[row][i].getValue()>0) {
				arr.remove(Integer.valueOf(sudoku.board[row][i].getValue()));
			}
		}

		return arr;
	}

}
