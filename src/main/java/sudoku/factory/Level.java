package sudoku.factory;

import java.util.ArrayList;

import sudoku.factory.SudokuFactory.State;

public class Level {
	protected int value;
	protected State[] squares;
	protected ArrayList<Integer> freeSquares;
	protected ArrayList<Integer> takenSquares;
	
	public Level(int value) {
		this.value = value;
		squares = new State[81];
		freeSquares = new ArrayList<Integer>();
		takenSquares = new ArrayList<Integer>();
		
		this.clear();
	}
	
	private void setSquareToIllegal(int square) {
		squares[square]=State.ILLEGAL;
		freeSquares.remove(Integer.valueOf(square));
	}
	
	private void setSquareToTaken(int square) {
		squares[square]=State.TAKEN;
		takenSquares.add(square);
		
		freeSquares.remove(Integer.valueOf(square));
		
		for (int i=0;i<9;i++) {
			// flip row to illegal
			if (squares[i + 9 * (square / 9)]==State.FREE) {
				setSquareToIllegal(i + 9 * (square / 9));
			}
			
			// flip col to illegal
			if (squares[i * 9 + (square % 9)]==State.FREE) {
				setSquareToIllegal(i * 9 + (square % 9));
			}
			
			// flip block to illegal
			int blockPos = findBlockPos(square, i);
			if (squares[blockPos]==State.FREE) {
				setSquareToIllegal(blockPos);
			}
		}
	}
	
	public boolean setValue(int square, State state) {
		if (squares[square]!=State.FREE) {
			return false;
		} else if (state == State.ILLEGAL) {
			setSquareToIllegal(square);
		} else if (state == State.TAKEN) {
			setSquareToTaken(square);
		}
		return true;
	}
	
	public boolean allocateFreeSquare(int freeSquareNo) {
		return setValue(freeSquares.get(freeSquareNo), State.TAKEN);
	}
	
	public int noOfFreeSquares() {
		return freeSquares.size();
	}
	
	protected int findBlockPos(int square, int i) {
		int retval = i % 3 + (i/3)*9;

		// col
		retval += 3 * ((square % 9) / 3);
		
		// row
		retval += 27 * (square / 27);
		
		return retval;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				buf.append(squares[i*9 + j]+",");
			}
			buf.append('\n');
		}
		return buf.toString();
	}
	
	public void clear() {
		freeSquares.clear();
		takenSquares.clear();
		
		for (int i=0;i<81;i++) {
			squares[i] = State.FREE;
			freeSquares.add(i);
		}
	}
}