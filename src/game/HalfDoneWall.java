package game;

import game.Board.State;

import java.awt.Graphics;

import top.Common;

public class HalfDoneWall implements GameObject {

	public enum Orientation {
		VERTICAL, HORIZONTAL
	};

	public enum Direction {
		POSITIVE, NEGATIVE
	}

	private BoardPos startpoint;
	private int length;
	private Orientation orientation;
	private Direction direction;

	private boolean removeable;
	private int stepdivider;
	private boolean done;

	private Game parent;

	public HalfDoneWall(Game g, BoardPos pos, Orientation o, Direction d) {
		stepdivider = 0;
		removeable = false;
		done = false;

		parent = g;
		startpoint = pos;
		orientation = o;
		direction = d;
		length = 0;

		if (parent.board.getState(startpoint) == State.WALL)
			removeable = true;
		else
			parent.board.setState(startpoint, State.UNDER_CONSTRUCTION);
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

	private void changeState(State s) {
		for (int i = 0; i <= length; i++) {
			BoardPos pos = getPos(i);
			parent.board.setState(pos, s);
		}
	}

	public void step() {
		if (removeable)
			return;

		if (stepdivider < Common.wallstepdivider)
			stepdivider++;

		else {
			stepdivider = 0;
			length++;

			BoardPos pos = getPos(length);

			if (parent.board.getState(pos) == State.WALL) {
				done = true;
				removeable = true;
			} else
				parent.board.setState(pos, State.UNDER_CONSTRUCTION);
		}
	}

	public void collide() {
		int i = 0;
		
		for (; i <= length; i++) {
			BoardPos pos = getPos(i);
			if (parent.board.getState(pos) == State.BROKEN_WALL) {
				changeState(State.EMPTY);
				removeable = true;
				parent.lives--;
				break;
			}
		}
		
		if (i > length && done)
			changeState(State.WALL);
	}

	public void paint(Graphics g) {
		/*
		 * Painting is done with painting the board, so we have nothing to do
		 * here.
		 */
	}

	public boolean isRemoveable() {
		return removeable;
	}

}
