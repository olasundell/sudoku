package sudoku.solver;

import sudoku.Sudoku;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: Feb 2, 2010
 * Time: 9:03:30 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Solver {
	public abstract boolean iterate(Sudoku sudoku);
	
	public void setSolution(int solution, Sudoku sudoku, int x, int y) {
		sudoku.board[y][x].setValue(solution);
		sudoku.board[y][x].removeCandidate(solution);
		sudoku.calculateCandidates();
	}
}
