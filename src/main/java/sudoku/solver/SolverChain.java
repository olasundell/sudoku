package sudoku.solver;

import sudoku.model.CellSolution;
import sudoku.model.Sudoku;
import sudoku.solver.supplier.CandidateSupplierProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class SolverChain {
	private final List<CandidateSupplierProvider> chain = new ArrayList<>();
	private final static SolverChain solverChain = new SolverChain();

	public boolean iterate(Sudoku sudoku) {
		for (int i = 0 ; i < 9 ; i++) {
			for (int j = 0; j < 9; j++) {
				CellSolution solution;
				for (CandidateSupplierProvider s: chain) {
					Supplier<Set<Integer>> supplier = s.getCandidateSupplier(sudoku, i, j);
					supplier.get();

//					try {
//						solution = SolverUtil.solveCell(sudoku.getCellAt(i, j), supplier);
//						sudoku.board[j][i].setValue(solution.getValue());
//						sudoku.board[j][i].removeCandidate(solution.getValue());
//						new CandidateCalculator().calculateCandidates(sudoku);
//					} catch (NoCellSolutionFoundException e) {
//						 ignore
//					}
				}
			}
		}

		return false;
	}

	public static SolverChain getSingleton() {
		return solverChain;
	}

	public void registerSupplierProvider(CandidateSupplierProvider provider) {
		chain.add(provider);
	}
}
