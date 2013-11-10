package game;

import top.Common;

public class BoardPos {
	
	public int xpos, ypos;
	
	public BoardPos(int x, int y) {
		xpos = x;
		ypos = y;
	}
	
	public static BoardPos vecToPos (Vector vec) {
		return new BoardPos((int) vec.x / Common.squaresize, (int) vec.y / Common.squaresize);
	}

}
