package sudoku.solver;

import sudoku.model.Sudoku;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: Feb 2, 2010
 * Time: 9:03:30 AM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public abstract class AbstractSolver implements Solution {
	private CandidateCalculator candidateCalculator = new CandidateCalculator();

	public void calculateCandidates(Sudoku sudoku) {
		candidateCalculator.calculateCandidates(sudoku);
	}

	@Override
	public void setSolution(int solution, Sudoku sudoku, int x, int y) {
		sudoku.board[y][x].setValue(solution);
		sudoku.board[y][x].removeCandidate(solution);
		candidateCalculator.calculateCandidates(sudoku);
	}

//	public int[][] trySolve(Sudoku sudoku) {
//		candidateCalculator.findCandidates(sudoku);
//		for (int i=0;i<9;i++) {
//			for (int j=0;j<9;j++) {
//				if (board[i][j].isUndecided()) {
//					calculateValueForSquare(i,j);
//				}
//			}
//
//		}
//		return getBoardAsInts();
//	}

}
