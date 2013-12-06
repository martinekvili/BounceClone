package game;

import game.Board.BoardState;

import java.awt.Color;
import java.awt.Graphics;

import top.Common;
import view.Place;

/**
 * Az �ppen �p�l� falat reprezent�l� oszt�ly.
 * 
 * Megval�s�tja a GameObject interface-t.
 */
public class HalfDoneWall implements GameObject {

	/**
	 * A lehets�ges �ll�sok.
	 */
	public enum Orientation {

		/**
		 * F�gg�leges ir�ny.
		 */
		VERTICAL,

		/**
		 * V�zszintes ir�ny.
		 */
		HORIZONTAL
	}

	/**
	 * A lehets�ges ir�nyok.
	 */
	public enum Direction {

		/**
		 * Pozit�v ir�nyba n�vekszik.
		 */
		POSITIVE,

		/**
		 * Negat�v ir�nyba n�vekszik.
		 */
		NEGATIVE
	}

	/**
	 * Az �p�l� fal lehets�ges �llapotai.
	 */
	private enum WallState {

		/**
		 * N�vekv�.
		 */
		GROWING,

		/**
		 * M�r nem tud tov�bb n�ni, k�vetkez� l�p�sben fel�p�l.
		 */
		ABOUT_TO_BUILD,

		/**
		 * Fel�p�lt, arra v�r, hogy a mellette l�v� �p�l� fal fel�p�l-e, mert ha
		 * igen akkor fel kell t�lteni az esetleges �res ter�leteket.
		 */
		BUILT,

		/**
		 * Fel�p�lt, �s m�r mellette sem �p�l m�sik fal, �gy t�r�lhet�.
		 */
		REMOVEABLE
	}

	/**
	 * A kezd�pontj�nak koordin�t�i.
	 */
	private BoardPos startpoint;

	/**
	 * Az aktu�lis hossz.
	 */
	private int length;

	/**
	 * A fal �ll�sa.
	 */
	private Orientation orientation;

	/**
	 * A n�veked�s ir�nya.
	 */
	private Direction direction;

	/**
	 * Az aktu�lis �llapot.
	 */
	private WallState state;

	/**
	 * Egy sz�ml�l�, hogy a fal lassabban �p�lj�n, mint ahogy a j�t�k t�rt�nik.
	 */
	private int stepdivider;

	/**
	 * A j�t�k ahol a fal �p�l.
	 */
	private Game parent;

	/**
	 * Konstruktor.
	 * 
	 * @param game
	 *            - a j�t�k ahol a fal �p�l
	 * @param pos
	 *            - a fal kezd�poz�ci�ja
	 * @param orientation
	 *            - a fal �ll�sa
	 * @param direction
	 *            - a n�veked�s ir�nya
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
	 * Egy seg�df�ggv�ny.
	 * 
	 * A kezd�poz�ci�t�l az itt megadott t�vols�gra l�v� cella koordin�t�it adja
	 * vissza, a fal �ll�sa �s n�veked�s�nek ir�nya alapj�n.
	 * 
	 * @param distance
	 *            - a t�vols�g
	 * @return a k�rd�ses cella koordin�t�i
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
	 * Egy seg�df�ggv�ny.
	 * 
	 * A kezd�poz�ci�t�l a megadott t�vols�gra l�v� cella melletti k�t cell�t
	 * adja vissza.
	 * 
	 * @param distance
	 *            - a t�vols�g
	 * @return a k�rd�ses k�t cella koordin�t�it tartalmaz� t�mb
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
	 * Az �p�l� fal ment�n be�ll�tja a p�ly�n a cell�k �llapot�t.
	 * 
	 * @param s
	 *            - a be�ll�tand� �llapot
	 */
	private void changeState(BoardState s) {
		for (int i = 0; i < length; i++) {
			BoardPos pos = getPos(i);
			parent.board.setState(pos, s);
		}
	}

	/**
	 * A l�ptet� f�ggv�ny.
	 * 
	 * Ha a fal n�vekv� �llapotban van, akkor n�velj�k eggyel a m�ret�t.
	 * 
	 * Ha viszont fel�p�lt �llapotban van, akkor ellen�rizz�k, hogy mellette
	 * �p�l-e m�g fal, �s ha m�r fel�p�lt, akkor elkezdj�k bet�lteni az �res
	 * ter�leteket.
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
	 * Az �tk�z�seket kezel� f�ggv�ny.
	 * 
	 * V�gigvizsg�lja a falat, hogy bele�tk�z�tt-e egy labda, ha igen akkor
	 * kit�rli mag�t.
	 * 
	 * Ha nem, �s a fal fel�p�thet� �llapotban van, akkor viszont fel�p�ti
	 * mag�t.
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
