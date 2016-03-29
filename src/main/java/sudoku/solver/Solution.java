package sudoku.solver;

import sudoku.model.Sudoku;

/**
 * TODO write documentation
 */
public interface Solution {
	void setSolution(int solution, Sudoku sudoku, int x, int y);
}
