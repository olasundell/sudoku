package sudoku.solver;

import sudoku.exception.NoCellSolutionFoundException;
import sudoku.model.Cell;
import sudoku.model.CellSolution;

import java.util.List;
import java.util.function.Supplier;

/**
 * TODO write documentation
 */
public class BlockSolver implements Solver {
	@Override
	public CellSolution solveCell(Cell currentCell, Supplier<List<Integer>> supplier) throws NoCellSolutionFoundException {
		List<Integer> blockCandidates = supplier.get();

		for (int k = 0; k < currentCell.candidates.size(); k++) {
			Integer c = currentCell.candidates.get(k);
			if (!blockCandidates.contains(c)) {
				return CellSolution.builder()
						.value(c)
						.build();
			}
		}

		throw new NoCellSolutionFoundException();
	}
}
