package game;

import top.Common;

/**
 * A p�ly�n l�v� cella koordin�t�it t�rol� oszt�ly.
 */
public class BoardPos {

	/**
	 * Az x koordin�ta.
	 */
	public int xpos;

	/**
	 * Az y koordin�ta.
	 */
	public int ypos;

	/**
	 * Konstruktor.
	 * 
	 * @param x
	 *            - x koordin�ta
	 * @param y
	 *            - y koordin�ta
	 */
	public BoardPos(int x, int y) {
		xpos = x;
		ypos = y;
	}

	/**
	 * F�ggv�ny, ami helyvektort konvert�l p�ly�n �rtelmezett cell�ra.
	 * 
	 * @param vec
	 *            - a helyvektor
	 * @return a p�lya cell�ja
	 */
	public static BoardPos vecToPos(Vector vec) {
		return new BoardPos((int) vec.x / Common.squaresize, (int) vec.y
				/ Common.squaresize);
	}

}
