package sudoku;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: Feb 1, 2010
 * Time: 1:02:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Cell {
	private int value;
	ArrayList<Integer> candidates;

	public Cell(int i) {
		value = i;
		candidates = new ArrayList<Integer>();
	}

	public boolean isUndecided() {
		return value==0;
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		if (value==0) {
			StringBuffer r=new StringBuffer("{");
			for (int i=0;i< candidates.size()-1;i++) {
				r.append(candidates.get(i));
				r.append(",");
			}

			if (candidates.size()>0) {
				r.append(candidates.get(candidates.size()-1));
			}
			
			r.append("}");
			return r.toString();
		} else {
			return String.valueOf(value);
		}
	}

	public void addCandidate(int k) {
		candidates.add(k);
	}

	public void removeCandidate(Integer k) {
		candidates.remove(k);
	}

	public boolean hasSingleCandidate() {
		return candidates.size()==1;
	}

	public Integer[] getCandidates() {
		return candidates.toArray(new Integer[candidates.size()]);
	}

	public void setValue(int value) {
		this.value=value;
		clearCandidates();
	}

	public void clearCandidates() {
		candidates.clear();
	}
}
