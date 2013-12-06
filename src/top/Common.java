package top;

/**
 * Az általános konstansokat tartalmazó osztály.
 * 
 * Azért vannak kigyûjtve, hogy a fejlesztés során könnyedén változtathatóak
 * legyenek.
 */
public class Common {

	/**
	 * A pálya oszlopainak száma.
	 */
	public static final int boardwidth = 30;

	/**
	 * A pálya sorainak száma.
	 */
	public static final int boardheight = 20;

	/**
	 * A cellákat reprezentáló négyzetek oldalhosszúsága (pixelben).
	 */
	public static final int squaresize = 21;

	/**
	 * A cellák közti elválasztóvonal szélessége (pixelben).
	 */
	public static final int delim = 1;

	/**
	 * A pálya szélessége (pixelben).
	 */
	public static final int width = boardwidth * squaresize + delim;

	/**
	 * A pálya magassága (pixelben).
	 */
	public static final int height = boardheight * squaresize + delim;

	/**
	 * A labdák sebessége.
	 */
	public static final double ballspeed = 5;

	/**
	 * A labdák mérete (a körülírt négyzetük oldalhosszúsága, pixelben).
	 */
	public static final int ballsize = 10;

	/**
	 * A fal épülésének lépésosztója.
	 */
	public static final int wallstepdivider = 7;

	/**
	 * A másodpercenként megjelenítendõ képek száma.
	 */
	public static final int framerate = 50;

}
