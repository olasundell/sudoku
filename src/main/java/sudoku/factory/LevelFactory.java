package sudoku.factory;

public class LevelFactory {
	protected static final LevelFactory defaultFactory = new LevelFactory();
	
	public LevelFactory getDefaultFactory() {
		return defaultFactory;
	}
	
	public Level createAndPopulateLevel(int level) {
		return null;
	}
}
