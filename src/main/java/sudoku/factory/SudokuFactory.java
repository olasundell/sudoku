package sudoku.factory;

import java.util.Random;

import org.apache.log4j.Logger;

import sudoku.model.Sudoku;

public class SudokuFactory {
	private static final int MAX_COMPLETE_BACKSTEPS = 100;
	private Random randomiser;
	private static SudokuFactory staticFactory = new SudokuFactory(0);
	private Level[] levels;
	Logger logger = Logger.getLogger(this.getClass());
	
	public SudokuFactory(long randseed) {
		randomiser = new Random();
		setSeed(randseed);
		levels = new Level[9];
		for (int i=0;i<9;i++) {
			levels[i] = new Level(i+1);
		}
	}
	
	public static SudokuFactory getInstance() {
		return staticFactory;
	}
	
	public enum State {
		FREE {
			public String toString() {
				return "F";
			}
		},
		
		TAKEN {
			public String toString() {
				return "T";
			}
		},
		ILLEGAL {
			public String toString() {
				return "X";
			}
		}
	}
	
	public Sudoku createSudoku() throws SudokuCreationException {
		int backStep = 1;
		int lastFailedLevel = -1;
		int noOfTimesFailed = 0;
		
		for (int i=0;i<9;i++) {
			try {
				for (int j=0;j<i;j++) {
				// get taken numbers from previous levels
					for (Integer k: levels[j].takenSquares) {
						if (!levels[i].setValue(k, State.ILLEGAL)) {
							// should not happen (famous last words)
							throw new LevelException();
						}
					}
				}
				// allocate nine numbers for this level
				for (int j=0;j<9;j++) {
					if (levels[i].noOfFreeSquares() <= 0) {
						throw new LevelException();
					}
					levels[i].allocateFreeSquare(randomiser.nextInt(levels[i].noOfFreeSquares()));
				}
			} catch (LevelException e) {
				// if this fails, step back backStep++ number of times and try again.
				if (lastFailedLevel == i) {
					backStep++;
				} else {
					backStep = 1;
					lastFailedLevel = i;
				}
				
				for (int j=i;j<i+backStep;j++) {
					levels[j].clear();
				}
				
				i-=backStep;
				
				if (i < 0) {
					if (noOfTimesFailed > MAX_COMPLETE_BACKSTEPS) {
						throw new SudokuCreationException("Failed to create a Sudoku, exiting to avoid endless loop");
					}
					noOfTimesFailed++;
					i = 0;
				}
				
				logger.debug("Backstepping. i: "+i+", backStep:"+backStep+", lastFailed:"+lastFailedLevel);
			}
		}
		
		Sudoku sudoku = new Sudoku(createIntArr());
		
		return sudoku;
	}

//	public Sudoku 
	
	protected int[][] createIntArr() {
		int[][] board = new int[9][9];
		int x, y;
		
		for (int i=0;i<9;i++) {
			for (Integer j: levels[i].takenSquares) {
				x = j / 9;
				y = j % 9;
				board[x][y] = levels[i].value;
			}
		}
		return board;
	}

	public void setSeed(long seed) {
		randomiser.setSeed(seed);
	}
}
