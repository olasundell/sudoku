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
public class BlockSupplierProvider extends AbstractCandidateSupplierProvider {
	public BlockSupplierProvider() {
		SolverChain.getSingleton().registerSupplierProvider(this);
	}

	@Override
	protected Set<Integer> createCandidates(Sudoku sudoku, int i, int j) {
		Set<Integer> blockCandidates = new HashSet<>();

		for (int  k = 0 ; k < 9 ; k++) {
			int x, y;

			y = i - i % 3 + k % 3;
			x = j - j % 3 + k / 3;

			if ((x != j || y != i) && sudoku.board[y][x].isUndecided()) {
				blockCandidates.addAll(sudoku.board[y][x].candidates);
			}
		}

		return blockCandidates;
	}
}
