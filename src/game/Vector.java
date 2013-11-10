package game;

import top.Common;

public class Vector {
	
	public double x, y;
	
	public Vector (double xpos, double ypos) {
		x = xpos;
		y = ypos;
	}
	
	public void add(Vector other) {
		x += other.x;
		y += other.y;
	}
	
	public static Vector posToVec (BoardPos pos) {
		return new Vector (pos.xpos * (Common.squaresize + Common.delim), pos.ypos * (Common.squaresize + Common.delim));
	}

}
