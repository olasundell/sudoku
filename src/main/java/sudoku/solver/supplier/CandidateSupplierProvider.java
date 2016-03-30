package sudoku.solver.supplier;

import sudoku.model.Candidates;
import sudoku.model.Sudoku;

import java.util.Set;
import java.util.function.Supplier;

/**
 * TODO write documentation
 */
public interface CandidateSupplierProvider {
	Supplier<Candidates> getCandidateSupplier(Sudoku sudoku, int i, int j);
}
