package game;

import game.Board.BoardState;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import top.Common;
import view.Place;

/**
 * A labdákat megvalósító osztály.
 * 
 * Megvalósítja a GameObject interface-t.
 */
public class Ball implements GameObject {

	/**
	 * A labda helye.
	 */
	private Vector place;

	/**
	 * A labda sebessége.
	 */
	private Vector speed;

	/**
	 * A játék, amiben a labda van.
	 */
	private Game parent;

	/**
	 * Konstruktor, ami véletlenszerû helyen és sebességgel hozza létre a
	 * labdát.
	 * 
	 * @param game
	 *            - a játék amiben a labda van
	 */
	public Ball(Game game) {
		parent = game;

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

	/**
	 * Az ütközéseket lekezelõ függvény.
	 * 
	 * Ha a labda egy felépült falhoz ütközik, akkor visszapattan róla. Ellenben
	 * ha egy még felépületlen falhoz ér, akkor jelzi, hogy a fal lebontandó.
	 */
	public void collide() {
		BoardPos pos;

		/* Függõleges ütközések vizsgálata. */
		if (speed.y < 0) {
			Vector top = new Vector(place.x + Common.ballsize / 2, place.y);
			pos = BoardPos.vecToPos(top);
		} else {
			Vector bottom = new Vector(place.x + Common.ballsize / 2, place.y
					+ Common.ballsize);
			pos = BoardPos.vecToPos(bottom);
		}

		if (parent.board.getState(pos) == BoardState.WALL) {
			speed.y *= -1;
		}

		/* Vízszintes ütközések vizsgálata. */
		if (speed.x < 0) {
			Vector left = new Vector(place.x, place.y + Common.ballsize / 2);
			pos = BoardPos.vecToPos(left);
		} else {
			Vector right = new Vector(place.x + Common.ballsize, place.y
					+ Common.ballsize / 2);
			pos = BoardPos.vecToPos(right);
		}

		if (parent.board.getState(pos) == BoardState.WALL) {
			speed.x *= -1;
		}

		/* A félig kész falakkal történt ütközés. */
		if (parent.board.getState(pos) == BoardState.UNDER_CONSTRUCTION)
			parent.board.setState(pos, BoardState.BROKEN_WALL);
	}

	/**
	 * A léptetõ függvény.
	 * 
	 * A labda aktuális helyét megváltoztatja a sebességének megfelelõ értékkel.
	 */
	public void step() {
		place.add(speed);
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		Place p = Place.vecToPlace(place);
		g.fillOval(p.x, p.y, Common.ballsize, Common.ballsize);
	}

	public boolean isRemoveable() {
		return false;
	}

	public Vector getVec() {
		return new Vector(place.x + Common.ballsize / 2, place.y
				+ Common.ballsize / 2);
	}

}
