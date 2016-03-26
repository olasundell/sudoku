package sudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cell {
	private int value;
	public final List<Integer> candidates;

	public Cell(int i) {
		value = i;
		candidates = new ArrayList<>();
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

	public Integer[] getCandidatesAsArray() {
		return candidates.toArray(new Integer[candidates.size()]);
	}

	public List<Integer> getCandidates() {
		return Collections.unmodifiableList(candidates);
	}

	public void setValue(int value) {
		this.value=value;
		clearCandidates();
	}

	public void clearCandidates() {
		candidates.clear();
	}
}
