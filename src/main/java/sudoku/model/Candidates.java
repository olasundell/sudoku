package sudoku.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.awt.Point;
import java.util.Set;

/**
 * TODO write documentation
 */
@Builder
@Getter
public class Candidates {
	private final Set<Integer> candidates;
	@Singular
	private final Set<Coordinate> cells;
}
