package sudoku.factory;

public class LevelFactory {
	protected static LevelFactory defaultFactory = new LevelFactory();
	
	public LevelFactory getDefaultFactory() {
		return defaultFactory;
	}
	
	public Level createAndPopulateLevel(int level) {
		return null;
	}
}
