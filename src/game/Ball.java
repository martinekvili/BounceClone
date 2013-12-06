package game;

import game.Board.BoardState;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import top.Common;
import view.Place;

/**
 * A labd�kat megval�s�t� oszt�ly.
 * 
 * Megval�s�tja a GameObject interface-t.
 */
public class Ball implements GameObject {

	/**
	 * A labda helye.
	 */
	private Vector place;

	/**
	 * A labda sebess�ge.
	 */
	private Vector speed;

	/**
	 * A j�t�k, amiben a labda van.
	 */
	private Game parent;

	/**
	 * Konstruktor, ami v�letlenszer� helyen �s sebess�ggel hozza l�tre a
	 * labd�t.
	 * 
	 * @param game
	 *            - a j�t�k amiben a labda van
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
	 * Az �tk�z�seket lekezel� f�ggv�ny.
	 * 
	 * Ha a labda egy fel�p�lt falhoz �tk�zik, akkor visszapattan r�la. Ellenben
	 * ha egy m�g fel�p�letlen falhoz �r, akkor jelzi, hogy a fal lebontand�.
	 */
	public void collide() {
		BoardPos pos;

		/* F�gg�leges �tk�z�sek vizsg�lata. */
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

		/* V�zszintes �tk�z�sek vizsg�lata. */
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

		/* A f�lig k�sz falakkal t�rt�nt �tk�z�s. */
		if (parent.board.getState(pos) == BoardState.UNDER_CONSTRUCTION)
			parent.board.setState(pos, BoardState.BROKEN_WALL);
	}

	/**
	 * A l�ptet� f�ggv�ny.
	 * 
	 * A labda aktu�lis hely�t megv�ltoztatja a sebess�g�nek megfelel� �rt�kkel.
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
