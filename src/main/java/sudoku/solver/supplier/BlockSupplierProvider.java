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
class BlockSupplierProvider extends AbstractCandidateSupplierProvider {
	static {
		SolverChain.getSingleton().registerSupplierProvider(new BlockSupplierProvider());
	}

	@Override
	protected Candidates createCandidates(Sudoku sudoku, int i, int j) {
		Candidates.CandidatesBuilder builder = Candidates.builder();
		Set<Integer> blockCandidates = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

		for (int  k = 0 ; k < 9 ; k++) {
			int x, y;

			y = i - i % 3 + k % 3;
			x = j - j % 3 + k / 3;

			Cell cell = sudoku.board[y][x];
			if (!cell.isUndecided()) {
				blockCandidates.remove(cell.getValue());
			} else {
				builder.cell(Coordinate.builder().x(x).y(y).build());
			}
		}

		return builder.candidates(blockCandidates).build();
	}
}
