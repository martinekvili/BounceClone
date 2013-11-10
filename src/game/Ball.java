package game;

import game.Board.State;

import java.awt.Graphics;
import java.util.Random;

import top.Common;
import view.Place;

public class Ball implements GameObject {

	private Vector place;
	private Vector speed;

	private Game parent;

	public Ball(Game g) {
		parent = g;

		Random rnd = new Random();

		place = new Vector(rnd.nextDouble()
				* (Common.width - Common.ballsize - 2 * Common.squaresize)
				+ Common.squaresize, rnd.nextDouble()
				* (Common.height - Common.ballsize - 2 * Common.squaresize)
				+ Common.squaresize);

		double angle = rnd.nextDouble() * 2 * Math.PI;
		speed = new Vector(Common.ballspeed * Math.cos(angle), Common.ballspeed
				* Math.sin(angle));

	}

	public void collide() {
		BoardPos pos;

		/* vertical */
		if (speed.y < 0) {
			Vector top = new Vector(place.x + Common.ballsize / 2, place.y);
			pos = BoardPos.vecToPos(top);
		} else {
			Vector bottom = new Vector(place.x + Common.ballsize / 2, place.y
					+ Common.ballsize);
			pos = BoardPos.vecToPos(bottom);
		}

		if (parent.board.getState(pos) == State.WALL) {
			speed.y *= -1;
		}

		/* horizontal */
		if (speed.x < 0) {
			Vector left = new Vector(place.x, place.y + Common.ballsize / 2);
			pos = BoardPos.vecToPos(left);
		} else {
			Vector right = new Vector(place.x + Common.ballsize, place.y
					+ Common.ballsize / 2);
			pos = BoardPos.vecToPos(right);
		}

		if (parent.board.getState(pos) == State.WALL) {
			speed.x *= -1;
		}

		/* anyways */
		if (parent.board.getState(pos) == State.UNDER_CONSTRUCTION)
			parent.board.setState(pos, State.BROKEN_WALL);
	}

	public void step() {
		place.add(speed);
	}

	public void paint(Graphics g) {
		Place p = Place.vecToPlace(place);
		g.fillOval(p.x, p.y, Common.ballsize, Common.ballsize);
	}

	public boolean isRemoveable() {
		return false;
	}

}
