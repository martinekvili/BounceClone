package game;

import game.Board.BoardState;

import java.awt.Color;
import java.awt.Graphics;

import top.Common;
import view.Place;

/**
 * Az éppen épülõ falat reprezentáló osztály.
 * 
 * Megvalósítja a GameObject interface-t.
 */
public class HalfDoneWall implements GameObject {

	/**
	 * A lehetséges állások.
	 */
	public enum Orientation {

		/**
		 * Függõleges irány.
		 */
		VERTICAL,

		/**
		 * Vízszintes irány.
		 */
		HORIZONTAL
	}

	/**
	 * A lehetséges irányok.
	 */
	public enum Direction {

		/**
		 * Pozitív irányba növekszik.
		 */
		POSITIVE,

		/**
		 * Negatív irányba növekszik.
		 */
		NEGATIVE
	}

	/**
	 * Az épülõ fal lehetséges állapotai.
	 */
	private enum WallState {

		/**
		 * Növekvõ.
		 */
		GROWING,

		/**
		 * Már nem tud tovább nõni, következõ lépésben felépül.
		 */
		ABOUT_TO_BUILD,

		/**
		 * Felépült, arra vár, hogy a mellette lévõ épülõ fal felépül-e, mert ha
		 * igen akkor fel kell tölteni az esetleges üres területeket.
		 */
		BUILT,

		/**
		 * Felépült, és már mellette sem épül másik fal, így törölhetõ.
		 */
		REMOVEABLE
	}

	/**
	 * A kezdõpontjának koordinátái.
	 */
	private BoardPos startpoint;

	/**
	 * Az aktuális hossz.
	 */
	private int length;

	/**
	 * A fal állása.
	 */
	private Orientation orientation;

	/**
	 * A növekedés iránya.
	 */
	private Direction direction;

	/**
	 * Az aktuális állapot.
	 */
	private WallState state;

	/**
	 * Egy számláló, hogy a fal lassabban épüljön, mint ahogy a játék történik.
	 */
	private int stepdivider;

	/**
	 * A játék ahol a fal épül.
	 */
	private Game parent;

	/**
	 * Konstruktor.
	 * 
	 * @param game
	 *            - a játék ahol a fal épül
	 * @param pos
	 *            - a fal kezdõpozíciója
	 * @param orientation
	 *            - a fal állása
	 * @param direction
	 *            - a növekedés iránya
	 */
	public HalfDoneWall(Game game, BoardPos pos, Orientation orientation,
			Direction direction) {
		stepdivider = 0;
		state = WallState.GROWING;

		parent = game;
		startpoint = pos;
		this.orientation = orientation;
		this.direction = direction;
		length = 1;

		parent.board.setState(startpoint, BoardState.UNDER_CONSTRUCTION);
	}

	/**
	 * Egy segédfüggvény.
	 * 
	 * A kezdõpozíciótól az itt megadott távolságra lévõ cella koordinátáit adja
	 * vissza, a fal állása és növekedésének iránya alapján.
	 * 
	 * @param distance
	 *            - a távolság
	 * @return a kérdéses cella koordinátái
	 */
	private BoardPos getPos(int distance) {
		int xd = 0, yd = 0;

		if (orientation == Orientation.VERTICAL)
			yd = distance;
		else
			xd = distance;

		if (direction == Direction.NEGATIVE) {
			xd *= -1;
			yd *= -1;
		}

		return new BoardPos(startpoint.xpos + xd, startpoint.ypos + yd);
	}

	/**
	 * Egy segédfüggvény.
	 * 
	 * A kezdõpozíciótól a megadott távolságra lévõ cella melletti két cellát
	 * adja vissza.
	 * 
	 * @param distance
	 *            - a távolság
	 * @return a kérdéses két cella koordinátáit tartalmazó tömb
	 */
	private BoardPos[] getPositions(int distance) {
		BoardPos[] tomb = new BoardPos[2];
		BoardPos pos = getPos(distance);

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

	/**
	 * Az épülõ fal mentén beállítja a pályán a cellák állapotát.
	 * 
	 * @param s
	 *            - a beállítandó állapot
	 */
	private void changeState(BoardState s) {
		for (int i = 0; i < length; i++) {
			BoardPos pos = getPos(i);
			parent.board.setState(pos, s);
		}
	}

	/**
	 * A léptetõ függvény.
	 * 
	 * Ha a fal növekvõ állapotban van, akkor növeljük eggyel a méretét.
	 * 
	 * Ha viszont felépült állapotban van, akkor ellenõrizzük, hogy mellette
	 * épül-e még fal, és ha már felépült, akkor elkezdjük betölteni az üres
	 * területeket.
	 */
	public void step() {
		switch (state) {
		case GROWING:
			if (stepdivider < Common.wallstepdivider)
				stepdivider++;

			else {
				stepdivider = 0;

				BoardPos pos = getPos(length);

				if (parent.board.getState(pos) == BoardState.WALL) {
					state = WallState.ABOUT_TO_BUILD;
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
				state = WallState.REMOVEABLE;
				break;

			case WALL:
				for (int i = 0; i < length; i++) {
					BoardPos[] positions = getPositions(i);

					for (BoardPos pos : positions)
						parent.board.fillFromPos(pos);
				}
				state = WallState.REMOVEABLE;
				break;

			default:
				break;
			}

			break;

		default:
			break;
		}
	}

	/**
	 * Az ütközéseket kezelõ függvény.
	 * 
	 * Végigvizsgálja a falat, hogy beleütközött-e egy labda, ha igen akkor
	 * kitörli magát.
	 * 
	 * Ha nem, és a fal felépíthetõ állapotban van, akkor viszont felépíti
	 * magát.
	 */
	public void collide() {
		for (int i = 0; i < length; i++) {
			BoardPos pos = getPos(i);

			if (parent.board.getState(pos) == BoardState.BROKEN_WALL) {
				changeState(BoardState.EMPTY);

				state = WallState.REMOVEABLE;

				parent.lives--;

				break;
			}
		}

		if (state == WallState.ABOUT_TO_BUILD) {
			changeState(BoardState.WALL);
			state = WallState.BUILT;
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
		return (state == WallState.REMOVEABLE);
	}

	public Vector getVec() {
		return null;
	}

}
