package sudoku.solver.supplier;

import sudoku.model.Sudoku;
import sudoku.solver.SolverChain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO write documentation
 */
public class RowSupplierProvider extends AbstractCandidateSupplierProvider {
	public RowSupplierProvider() {
		SolverChain.getSingleton().registerSupplierProvider(this);
	}

	@Override
	protected Set<Integer> createCandidates(Sudoku sudoku, int i, int j) {
			Set<Integer> rowCandidates = new HashSet<>();

			for (int k=0;k<9;k++) {
				if (k!= j) { // check row
					rowCandidates.addAll(sudoku.board[i][k].candidates);
				}
			}

			return rowCandidates;
	}
}
