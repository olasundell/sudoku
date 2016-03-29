package sudoku.solver.supplier;

import sudoku.model.Sudoku;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * TODO write documentation
 */
abstract class AbstractCandidateSupplierProvider implements CandidateSupplierProvider {
	@Override
	public final Supplier<Set<Integer>> getCandidateSupplier(Sudoku sudoku, int i, int j) {
		return () -> this.createCandidates(sudoku, i, j);
	}

	protected abstract Set<Integer> createCandidates(Sudoku sudoku, int i, int j);
}
