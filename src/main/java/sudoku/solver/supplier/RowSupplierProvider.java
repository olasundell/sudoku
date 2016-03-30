package sudoku.solver.supplier;

import sudoku.model.Cell;
import sudoku.model.Sudoku;
import sudoku.solver.SolverChain;

import java.util.*;

/**
 * TODO write documentation
 */
class RowSupplierProvider extends AbstractCandidateSupplierProvider {
	static {
		SolverChain.getSingleton().registerSupplierProvider(new RowSupplierProvider());
	}

	@Override
	protected Set<Integer> createCandidates(Sudoku sudoku, int i, int j) {
			Set<Integer> rowCandidates = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

			for (int k=0;k<9;k++) {
				Cell cell = sudoku.board[j][k];
				if (!cell.isUndecided()) {
					rowCandidates.remove(cell.getValue());
				}
			}

			return rowCandidates;
	}
}
