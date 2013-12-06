package game;

/**
 * Vektort t�rol� oszt�ly.
 */
public class Vector {

	/**
	 * Az x koordin�ta.
	 */
	public double x;

	/**
	 * Az y koordin�ta.
	 */
	public double y;

	/**
	 * Konstruktor.
	 * 
	 * @param xpos
	 *            - x koordin�ta
	 * @param ypos
	 *            - y koordin�ta
	 */
	public Vector(double xpos, double ypos) {
		x = xpos;
		y = ypos;
	}

	/**
	 * Vektorokat �sszead� f�ggv�ny.
	 * 
	 * @param other
	 *            - az �sszeg m�sik tagja
	 */
	public void add(Vector other) {
		x += other.x;
		y += other.y;
	}

}
