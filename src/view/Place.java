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
		return new Place((int) vec.x + Common.border, (int) vec.y
				+ Common.border);
	}

	public static Place posToPlace(BoardPos pos) {
		return new Place((pos.xpos + 1) * (Common.squaresize + Common.delim) + Common.delim,
				(pos.ypos + 1) * (Common.squaresize + Common.delim) + Common.delim);
	}
}
