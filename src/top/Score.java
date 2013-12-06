package top;

import java.io.Serializable;

/**
 * A pontokat tartalmaz� oszt�ly.
 */
public class Score implements Serializable, Comparable<Score> {

	private static final long serialVersionUID = 1L;

	/**
	 * A pontsz�m.
	 */
	public int points;

	/**
	 * A pontsz�mot el�rt illet� neve.
	 */
	public String name;

	/**
	 * Konstruktor.
	 * 
	 * @param name
	 *            - az illet� neve
	 * @param points
	 *            - az el�rt pontok sz�ma
	 */
	public Score(String name, int points) {
		this.name = name;
		this.points = points;
	}

	/**
	 * A kompar�l�shoz sz�ks�ges f�ggv�ny.
	 * 
	 * Az el�rt pontsz�m �ltal rendez cs�kken� sorrendbe.
	 */
	public int compareTo(Score arg0) {
		return Integer.compare(arg0.points, points);
	}

}
