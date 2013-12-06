package game;

import top.Common;

/**
 * A pályán lévõ cella koordinátáit tároló osztály.
 */
public class BoardPos {

	/**
	 * Az x koordináta.
	 */
	public int xpos;

	/**
	 * Az y koordináta.
	 */
	public int ypos;

	/**
	 * Konstruktor.
	 * 
	 * @param x
	 *            - x koordináta
	 * @param y
	 *            - y koordináta
	 */
	public BoardPos(int x, int y) {
		xpos = x;
		ypos = y;
	}

	/**
	 * Függvény, ami helyvektort konvertál pályán értelmezett cellára.
	 * 
	 * @param vec
	 *            - a helyvektor
	 * @return a pálya cellája
	 */
	public static BoardPos vecToPos(Vector vec) {
		return new BoardPos((int) vec.x / Common.squaresize, (int) vec.y
				/ Common.squaresize);
	}

}
