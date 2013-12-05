package top;

import java.io.Serializable;

public class Score implements Serializable, Comparable<Score> {

	private static final long serialVersionUID = 1L;

	public int points;
	public String name;

	public Score(String s, int p) {
		name = s;
		points = p;
	}

	public int compareTo(Score arg0) {
		return Integer.compare(arg0.points, points);
	}

	public String toString() {
		return name + ": " + points;
	}

}
