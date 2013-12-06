package game;

import java.awt.Graphics;

/**
 * A j�t�kban megjelen� objektumokat �sszefog� interface.
 */
public interface GameObject {

	/**
	 * A l�ptet� f�ggv�ny.
	 */
	public abstract void step();

	/**
	 * Az �tk�z�seket lekezel� f�ggv�ny.
	 */
	public abstract void collide();

	/**
	 * A kirajzol� f�ggv�ny.
	 * 
	 * @param g
	 *            - a Graphics amire rajzolunk
	 */
	public abstract void paint(Graphics g);

	/**
	 * A f�ggv�ny ami megmondja, hogy az objektum t�r�lhet�-e.
	 * 
	 * @return igaz, ha az objektum t�r�lhet�
	 */
	public abstract boolean isRemoveable();

	/**
	 * Megadja az objektum hely�t Vector seg�ts�g�vel, ha ez �rtelmes.
	 * 
	 * @return az objektum helye
	 */
	public abstract Vector getVec();

}
