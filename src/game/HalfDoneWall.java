package game;

import game.Board.State;

import java.awt.Color;
import java.awt.Graphics;

import top.Common;
import view.Place;

public class HalfDoneWall implements GameObject {
	
	private static int classnum = 0;
	
	private int num;

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
		num = classnum++;
		
		stepdivider = 0;
		removeable = false;
		done = false;

		parent = g;
		startpoint = pos;
		orientation = o;
		direction = d;
		length = 1;

		parent.board.setState(startpoint, State.UNDER_CONSTRUCTION);
		
		System.out.println(num + ": " + startpoint.xpos + ", " + startpoint.ypos);
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
		System.out.println(num + " start: " + startpoint.xpos + ", " + startpoint.ypos + ", length: " + length);
		for (int i = 0; i < length; i++) {
			BoardPos pos = getPos(i);
			System.out.println(pos.xpos + ", " + pos.ypos);
			parent.board.setState(pos, s);
		}
	}

	public void step() {
		if (removeable)
			return;

		if (stepdivider < Common.wallstepdivider)
			stepdivider++;

		else {
			System.out.println("I'm alive. " + num + " start: " + startpoint.xpos + ", " + startpoint.ypos + ", length: " + length);
			//System.out.println(num + " start: " + startpoint.xpos + ", " + startpoint.ypos + ", length: " + length);
			
			stepdivider = 0;

			BoardPos pos = getPos(length);

			if (parent.board.getState(pos) == State.WALL) {
				done = true;
				removeable = true;
			} else {
				parent.board.setState(pos, State.UNDER_CONSTRUCTION);
				length++;
			}
		}
	}

	public void collide() {
		boolean collided = false;
		
		for (int i = 0; i < length; i++) {
			BoardPos pos = getPos(i);
			if (parent.board.getState(pos) == State.BROKEN_WALL) {
				changeState(State.EMPTY);
				removeable = true;
				collided = true;
				parent.lives--;
				break;
			}
		}
		
		if (done && !collided)
			changeState(State.WALL);
	}

	public void paint(Graphics g) {
		/*
		 * Painting is done with painting the board, so we have nothing to do
		 * here.
		 */
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
		return removeable;
	}

}
