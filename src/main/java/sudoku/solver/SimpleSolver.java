package sudoku.solver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.model.Sudoku;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: Feb 1, 2010
 * Time: 2:07:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleSolver extends Solver {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	public boolean iterate(Sudoku sudoku) {
		for (int i=0;i<sudoku.board.length;i++) {
			for (int j=0;j<sudoku.board[i].length;j++) {
				if (sudoku.board[i][j].hasSingleCandidate()) {
					setSolution(sudoku.board[i][j].getCandidatesAsArray()[0], sudoku, j, i);
					return true;
				}
			}
		}
		logger.warn("SimpleSolver couldn't find a candidate good enough!");
		return false;
	}
}
