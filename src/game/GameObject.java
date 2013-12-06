package game;

import java.awt.Graphics;

/**
 * A játékban megjelenõ objektumokat összefogó interface.
 */
public interface GameObject {

	/**
	 * A léptetõ függvény.
	 */
	public abstract void step();

	/**
	 * Az ütközéseket lekezelõ függvény.
	 */
	public abstract void collide();

	/**
	 * A kirajzoló függvény.
	 * 
	 * @param g
	 *            - a Graphics amire rajzolunk
	 */
	public abstract void paint(Graphics g);

	/**
	 * A függvény ami megmondja, hogy az objektum törölhetõ-e.
	 * 
	 * @return igaz, ha az objektum törölhetõ
	 */
	public abstract boolean isRemoveable();

	/**
	 * Megadja az objektum helyét Vector segítségével, ha ez értelmes.
	 * 
	 * @return az objektum helye
	 */
	public abstract Vector getVec();

}
