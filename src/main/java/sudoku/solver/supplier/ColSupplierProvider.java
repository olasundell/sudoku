package sudoku.solver.supplier;

import sudoku.model.Candidates;
import sudoku.model.Cell;
import sudoku.model.Coordinate;
import sudoku.model.Sudoku;
import sudoku.solver.SolverChain;

import java.awt.*;
import java.util.*;

/**
 * TODO write documentation
 */
class ColSupplierProvider extends AbstractCandidateSupplierProvider {
	static {
		SolverChain.getSingleton().registerSupplierProvider(new ColSupplierProvider());
	}

	@Override
	protected Candidates createCandidates(Sudoku sudoku, int i, int j) {
		Candidates.CandidatesBuilder builder = Candidates.builder();

		Set<Integer> colCandidates= new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

		for (int k = 0 ; k < 9 ; k++) {
			Cell cell = sudoku.board[k][j];
			if (!cell.isUndecided()) {
				colCandidates.remove(cell.getValue());
			} else {
				builder.cell(Coordinate.builder().x(j).y(k).build());
			}
		}

		return builder.candidates(colCandidates).build();
	}
}
