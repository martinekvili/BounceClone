package top;

/**
 * Az �ltal�nos konstansokat tartalmaz� oszt�ly.
 * 
 * Az�rt vannak kigy�jtve, hogy a fejleszt�s sor�n k�nnyed�n v�ltoztathat�ak
 * legyenek.
 */
public class Common {

	/**
	 * A p�lya oszlopainak sz�ma.
	 */
	public static final int boardwidth = 30;

	/**
	 * A p�lya sorainak sz�ma.
	 */
	public static final int boardheight = 20;

	/**
	 * A cell�kat reprezent�l� n�gyzetek oldalhossz�s�ga (pixelben).
	 */
	public static final int squaresize = 21;

	/**
	 * A cell�k k�zti elv�laszt�vonal sz�less�ge (pixelben).
	 */
	public static final int delim = 1;

	/**
	 * A p�lya sz�less�ge (pixelben).
	 */
	public static final int width = boardwidth * squaresize + delim;

	/**
	 * A p�lya magass�ga (pixelben).
	 */
	public static final int height = boardheight * squaresize + delim;

	/**
	 * A labd�k sebess�ge.
	 */
	public static final double ballspeed = 5;

	/**
	 * A labd�k m�rete (a k�r�l�rt n�gyzet�k oldalhossz�s�ga, pixelben).
	 */
	public static final int ballsize = 10;

	/**
	 * A fal �p�l�s�nek l�p�soszt�ja.
	 */
	public static final int wallstepdivider = 7;

	/**
	 * A m�sodpercenk�nt megjelen�tend� k�pek sz�ma.
	 */
	public static final int framerate = 50;

}
