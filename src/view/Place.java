package view;

import game.BoardPos;
import game.Vector;
import top.Common;

public class Place {

	public int x, y;

	public Place(int xpos, int ypos) {
		x = xpos;
		y = ypos;
	}

	public static Place vecToPlace(Vector vec) {
		return new Place((int) vec.x, (int) vec.y);
	}

	public static Place posToPlace(BoardPos pos) {
		return new Place(pos.xpos * Common.squaresize + Common.delim, pos.ypos
				* Common.squaresize + Common.delim);
	}
}
