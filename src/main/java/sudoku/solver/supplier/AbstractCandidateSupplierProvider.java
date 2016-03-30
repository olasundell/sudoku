package sudoku.solver.supplier;

import sudoku.model.Candidates;
import sudoku.model.Sudoku;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * TODO write documentation
 */
abstract class AbstractCandidateSupplierProvider implements CandidateSupplierProvider {
	@Override
	public final Supplier<Candidates> getCandidateSupplier(Sudoku sudoku, int i, int j) {
		return () -> this.createCandidates(sudoku, i, j);
	}

	protected abstract Candidates createCandidates(Sudoku sudoku, int i, int j);
}
