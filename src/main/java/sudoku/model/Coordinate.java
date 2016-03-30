package sudoku.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * TODO write documentation
 */
@Builder
@Getter
@EqualsAndHashCode
public class Coordinate {
	private final int x;
	private final int y;

	@Override
	public String toString() {
		return String.format("(%s,%s)", x, y);
	}
}
