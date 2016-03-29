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
public class ColSupplierProvider extends AbstractCandidateSupplierProvider {
	public ColSupplierProvider() {
		SolverChain.getSingleton().registerSupplierProvider(this);
	}

	@Override
	protected Set<Integer> createCandidates(Sudoku sudoku, int i, int j) {
		Set<Integer> colCandidates= new HashSet<>();

		for (int k=0;k<9;k++) {
			if (k!= i) { // check col
				colCandidates.addAll(sudoku.board[k][j].candidates);
			}
		}

		return colCandidates;
	}
}
