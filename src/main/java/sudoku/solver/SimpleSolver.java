package sudoku.solver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.model.Sudoku;

/**
 * Checks for cells where there is only one candidate, which is very cheap.
 */
public class SimpleSolver extends AbstractSolver {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public boolean iterate(Sudoku sudoku) {
		for (int i = 0 ; i < sudoku.board.length ; i++) {
			for (int j = 0 ; j < sudoku.board[i].length ; j++) {
				if (sudoku.board[i][j].hasSingleCandidate()) {
					this.setSolution(sudoku.board[i][j].getCandidatesAsArray()[0], sudoku, j, i);
					return true;
				}
			}
		}
		logger.warn("SimpleSolver couldn't find a candidate good enough!");
		return false;
	}
}
