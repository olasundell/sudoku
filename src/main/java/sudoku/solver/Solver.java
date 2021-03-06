package sudoku.solver;

import sudoku.exception.NoCellSolutionFoundException;
import sudoku.model.Cell;
import sudoku.model.CellSolution;
import sudoku.model.Sudoku;

import java.util.List;
import java.util.function.Supplier;

/**
 * TODO write documentation
 */
public interface Solver {
	CellSolution solveCell(Cell currentCell, Supplier<List<Integer>> supplier) throws NoCellSolutionFoundException;
}
