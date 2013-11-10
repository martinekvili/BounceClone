package top;

public class Common {
	
	/* Size of the board: in squares. */
	public static final int boardwidth = 28;
	public static final int boardheight = 18;
	
	/* Size of the squares: in pixels. */
	public static final int squaresize = 20;
	
	/* Size of the gap between squares: in pixels. */
	public static final int delim = 1;
	
	/* Size of the border. */
	public static final int border = squaresize + 2 * delim;
	
	/* Size of the board: in pixels. */
	public static final int width = boardwidth * (squaresize + delim);
	public static final int height = boardheight * (squaresize + delim);
	
	/* The speed of the balls. */
	public static final double ballspeed = 5;
	
	public static final int ballsize = 10;

}
