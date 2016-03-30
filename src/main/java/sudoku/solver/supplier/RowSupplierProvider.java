package sudoku.solver.supplier;

import sudoku.model.Candidates;
import sudoku.model.Cell;
import sudoku.model.Sudoku;
import sudoku.solver.SolverChain;

import java.awt.*;
import java.util.*;

/**
 * TODO write documentation
 */
class RowSupplierProvider extends AbstractCandidateSupplierProvider {
	static {
		SolverChain.getSingleton().registerSupplierProvider(new RowSupplierProvider());
	}

	@Override
	protected Candidates createCandidates(Sudoku sudoku, int i, int j) {
		Candidates.CandidatesBuilder builder = Candidates.builder();
		Set<Integer> rowCandidates = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

		for (int k=0;k<9;k++) {
			Cell cell = sudoku.board[j][k];
			if (!cell.isUndecided()) {
				rowCandidates.remove(cell.getValue());
			} else {
				builder.cell(new Point(j, k));
			}
		}

		return builder.candidates(rowCandidates).build();
	}
}
