package sudoku.solver;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * TODO write documentation
 */
public class SolverChainTest {
	SolverChain chain;

	@BeforeMethod
	public void setup() {
		chain = new SolverChain();
	}

	@Test
	public void shouldWork() {
		Assert.assertTrue(true);
	}
}