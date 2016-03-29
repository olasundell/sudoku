package sudoku.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * TODO write documentation
 */
@Builder
@Getter
@ToString
public class CellSolution {
	private final int value;
	private final int i;
	private final int j;
}
