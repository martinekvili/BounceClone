package view;

import game.BoardPos;
import game.Vector;
import top.Common;

/**
 * Megjeleníthetõ koordinátákat tartalmazó osztály.
 */
public class Place {

	/**
	 * Az x koordináta.
	 */
	public int x;

	/**
	 * Az y koordináta.
	 */
	public int y;

	/**
	 * Konstruktor.
	 * 
	 * @param xpos
	 *            - x koordináta
	 * @param ypos
	 *            - y koordináta
	 */
	public Place(int xpos, int ypos) {
		x = xpos;
		y = ypos;
	}

	/**
	 * Vektort megjeleníthetõ koordinátává konvertáló függvény.
	 * 
	 * @param vec
	 *            - a vektor
	 * @return a megjeleníthetõ koordináták
	 */
	public static Place vecToPlace(Vector vec) {
		return new Place((int) vec.x, (int) vec.y);
	}

	/**
	 * Pályán lévõ cellakoordinátát megjeleníthetõ koordinátára konvertáló
	 * függvény.
	 * 
	 * @param pos
	 *            - a cella koordinátái
	 * @return a megjeleníthetõ koordináták
	 */
	public static Place posToPlace(BoardPos pos) {
		return new Place(pos.xpos * Common.squaresize + Common.delim, pos.ypos
				* Common.squaresize + Common.delim);
	}
}
