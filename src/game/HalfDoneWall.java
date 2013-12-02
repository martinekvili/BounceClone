package game;

import game.Board.BoardState;

import java.awt.Color;
import java.awt.Graphics;

import top.Common;
import view.Place;

public class HalfDoneWall implements GameObject {

	public enum Orientation {
		VERTICAL, HORIZONTAL
	}

	public enum Direction {
		POSITIVE, NEGATIVE
	}

	private enum WallState {
		GROWING, ABOUT_TO_BUILD, BUILT, REMOVEABLE
	}

	private BoardPos startpoint;
	private int length;
	private Orientation orientation;
	private Direction direction;

	private WallState status;

	private int stepdivider;

	private Game parent;

	public HalfDoneWall(Game g, BoardPos pos, Orientation o, Direction d) {
		stepdivider = 0;
		status = WallState.GROWING;

		parent = g;
		startpoint = pos;
		orientation = o;
		direction = d;
		length = 1;

		parent.board.setState(startpoint, BoardState.UNDER_CONSTRUCTION);
	}

	private BoardPos getPos(int i) {
		int xd = 0, yd = 0;

		if (orientation == Orientation.VERTICAL)
			yd = i;
		else
			xd = i;

		if (direction == Direction.NEGATIVE) {
			xd *= -1;
			yd *= -1;
		}

		return new BoardPos(startpoint.xpos + xd, startpoint.ypos + yd);
	}

	private BoardPos[] getPositions(int i) {
		BoardPos[] tomb = new BoardPos[2];
		BoardPos pos = getPos(i);

		int dx = 0, dy = 0;

		if (orientation == Orientation.HORIZONTAL)
			dy = 1;
		else
			dx = 1;

		tomb[0] = new BoardPos(pos.xpos + dx, pos.ypos + dy);

		dx *= -1;
		dy *= -1;

		tomb[1] = new BoardPos(pos.xpos + dx, pos.ypos + dy);

		return tomb;
	}

	private void changeState(BoardState s) {
		for (int i = 0; i < length; i++) {
			BoardPos pos = getPos(i);
			parent.board.setState(pos, s);
		}
	}

	public void step() {
		switch (status) {
		case GROWING:
			if (stepdivider < Common.wallstepdivider)
				stepdivider++;

			else {
				stepdivider = 0;

				BoardPos pos = getPos(length);

				if (parent.board.getState(pos) == BoardState.WALL) {
					status = WallState.ABOUT_TO_BUILD;
				} else {
					parent.board.setState(pos, BoardState.UNDER_CONSTRUCTION);
					length++;
				}
			}
			break;

		case BUILT:
			BoardPos otherwallstartpos = getPos(-1);

			switch (parent.board.getState(otherwallstartpos)) {
			case EMPTY:
				status = WallState.REMOVEABLE;
				break;

			case WALL:
				for (int i = 0; i < length; i++) {
					BoardPos[] positions = getPositions(i);

					for (BoardPos pos : positions)
						parent.board.fillFromPos(pos);
				}
				status = WallState.REMOVEABLE;
				break;

			default:
				break;
			}

			break;

		default:
			break;
		}
	}

	public void collide() {
		for (int i = 0; i < length; i++) {
			BoardPos pos = getPos(i);

			if (parent.board.getState(pos) == BoardState.BROKEN_WALL) {
				changeState(BoardState.EMPTY);

				status = WallState.REMOVEABLE;

				parent.lives--;

				break;
			}
		}

		if (status == WallState.ABOUT_TO_BUILD) {
			changeState(BoardState.WALL);
			status = WallState.BUILT;
		}
	}

	public void paint(Graphics g) {
		if (direction == Direction.POSITIVE)
			g.setColor(Color.YELLOW);
		else
			g.setColor(Color.ORANGE);

		for (int i = 0; i < length; i++) {
			Place p = Place.posToPlace(getPos(i));
			g.fillRect(p.x, p.y, Common.squaresize - Common.delim,
					Common.squaresize - Common.delim);
		}
	}

	public boolean isRemoveable() {
		return (status == WallState.REMOVEABLE);
	}

	public Vector getVec() {
		return null;
	}

}
