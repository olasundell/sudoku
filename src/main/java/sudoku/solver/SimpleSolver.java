package sudoku.solver;

import sudoku.Sudoku;
import sudoku.solver.Solver;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: Feb 1, 2010
 * Time: 2:07:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleSolver extends Solver {
	public boolean iterate(Sudoku sudoku) {
		for (int i=0;i<sudoku.board.length;i++) {
			for (int j=0;j<sudoku.board[i].length;j++) {
				if (sudoku.board[i][j].hasSingleCandidate()) {
					setSolution(sudoku.board[i][j].getCandidatesAsArray()[0], sudoku, j, i);
					return true;
				}
			}
		}
		System.err.println("SimpleSolver couldn't find a candidate good enough!");
		return false;
	}
}