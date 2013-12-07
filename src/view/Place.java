package view;

import game.BoardPos;
import game.Vector;
import top.Common;

/**
 * Megjelen�thet� koordin�t�kat tartalmaz� oszt�ly.
 */
public class Place {

	/**
	 * Az x koordin�ta.
	 */
	public int x;

	/**
	 * Az y koordin�ta.
	 */
	public int y;

	/**
	 * Konstruktor.
	 * 
	 * @param xpos
	 *            - x koordin�ta
	 * @param ypos
	 *            - y koordin�ta
	 */
	public Place(int xpos, int ypos) {
		x = xpos;
		y = ypos;
	}

	/**
	 * Vektort megjelen�thet� koordin�t�v� konvert�l� f�ggv�ny.
	 * 
	 * @param vec
	 *            - a vektor
	 * @return a megjelen�thet� koordin�t�k
	 */
	public static Place vecToPlace(Vector vec) {
		return new Place((int) vec.x, (int) vec.y);
	}

	/**
	 * P�ly�n l�v� cellakoordin�t�t megjelen�thet� koordin�t�ra konvert�l�
	 * f�ggv�ny.
	 * 
	 * @param pos
	 *            - a cella koordin�t�i
	 * @return a megjelen�thet� koordin�t�k
	 */
	public static Place posToPlace(BoardPos pos) {
		return new Place(pos.xpos * Common.squaresize + Common.delim, pos.ypos
				* Common.squaresize + Common.delim);
	}
}
