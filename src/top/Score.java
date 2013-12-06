package top;

import java.io.Serializable;

/**
 * A pontokat tartalmazó osztály.
 */
public class Score implements Serializable, Comparable<Score> {

	private static final long serialVersionUID = 1L;

	/**
	 * A pontszám.
	 */
	public int points;

	/**
	 * A pontszámot elért illetõ neve.
	 */
	public String name;

	/**
	 * Konstruktor.
	 * 
	 * @param name
	 *            - az illetõ neve
	 * @param points
	 *            - az elért pontok száma
	 */
	public Score(String name, int points) {
		this.name = name;
		this.points = points;
	}

	/**
	 * A komparáláshoz szükséges függvény.
	 * 
	 * Az elért pontszám által rendez csökkenõ sorrendbe.
	 */
	public int compareTo(Score arg0) {
		return Integer.compare(arg0.points, points);
	}

}
