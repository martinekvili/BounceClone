package game;

/**
 * Vektort tároló osztály.
 */
public class Vector {

	/**
	 * Az x koordináta.
	 */
	public double x;

	/**
	 * Az y koordináta.
	 */
	public double y;

	/**
	 * Konstruktor.
	 * 
	 * @param xpos
	 *            - x koordináta
	 * @param ypos
	 *            - y koordináta
	 */
	public Vector(double xpos, double ypos) {
		x = xpos;
		y = ypos;
	}

	/**
	 * Vektorokat összeadó függvény.
	 * 
	 * @param other
	 *            - az összeg másik tagja
	 */
	public void add(Vector other) {
		x += other.x;
		y += other.y;
	}

}
