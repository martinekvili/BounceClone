package game;

import game.Board.BoardState;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import top.Common;
import view.Place;

/**
 * A játékot reprezentáló osztály.
 */
public class Game {

	/**
	 * A játék állapotai.
	 */
	public enum GameState {
		/**
		 * Éppen fut.
		 */
		RUNNING,

		/**
		 * Szünetel.
		 */
		PAUSED,

		/**
		 * A játék véget ért, vesztettünk.
		 */
		OVER,

		/**
		 * A játék véget ért, nyertünk.
		 */
		WON
	}

	/**
	 * A hátralévõ idõ, másodpercekben.
	 */
	private int time;

	/**
	 * A pontjaink száma.
	 */
	private int points;

	/**
	 * A meglévõ életek száma.
	 */
	int lives;

	/**
	 * A pályán lévõ labdák száma.
	 */
	int balls;

	/**
	 * A pálya.
	 */
	public Board board;

	/**
	 * A játék aktuális állapota.
	 */
	public GameState state;

	/**
	 * A játékban lévõ objektumok listája.
	 */
	List<GameObject> objects;

	/**
	 * Konstruktor, ami a szint alapján beállít minden értéket.
	 * 
	 * Beállítja az idõt, az életek számát, létrehoz megfelelõ mennyiségû
	 * labdát, inicializálja a pályát és végül futó állapotba állítja önmagát.
	 * 
	 * @param level
	 *            - az aktuális szint
	 * @param points
	 *            - a már meglévõ pontjaink száma
	 */
	public Game(int level, int points) {
		this.points = points;

		time = 90 + level * 30;
		lives = 2 + level;
		balls = 2 + level;

		board = new Board(this);

		objects = new ArrayList<GameObject>();

		for (int i = 0; i < balls; i++)
			objects.add(new Ball(this));

		state = GameState.RUNNING;
	}

	/**
	 * Függvény, amellyel újabb objektumot adhatunk a játékhoz.
	 * 
	 * @param o
	 *            - az új objektum
	 */
	public void addObject(GameObject o) {
		objects.add(o);
	}

	/**
	 * Léptetõ függvény.
	 * 
	 * Végigmegy a játék összes objektumán, és lépteti õket, majd ellenõrizteti
	 * az esetleges ütközéseket. Ezek után kitörli a már szükségtelen
	 * objektumokat. A végén ellenõrzi, hogy nyertünk avagy veszítettünk-e, és
	 * ha igen, akkor ennek megfelelõen beállítja a játék állapotát.
	 */
	public void step() {
		if (state == GameState.RUNNING) {
			for (GameObject o : objects) {
				o.step();
				o.collide();
			}

			Iterator<GameObject> i = objects.iterator();
			while (i.hasNext()) {
				if (i.next().isRemoveable())
					i.remove();
			}
		}

		if (lives <= 0 || time <= 0)
			state = GameState.OVER;
		else if (board.getPercent() >= 75)
			state = GameState.WON;
	}

	/**
	 * Kirajzolja a játékban található objektumokat.
	 * 
	 * @param g
	 *            - a Graphics amire rajzolunk
	 */
	public void paintObjects(Graphics g) {
		for (GameObject o : objects)
			o.paint(g);
	}

	/**
	 * Kirajzolja a pályát.
	 * 
	 * @param g
	 *            - a Graphics amire rajzolunk
	 */
	public void paintBoard(Graphics g) {
		for (int i = 0; i < Common.boardheight; i++) {
			for (int j = 0; j < Common.boardwidth; j++) {
				BoardPos pos = new BoardPos(j, i);
				Place p = Place.posToPlace(pos);

				if (board.getState(pos) == BoardState.WALL) {
					g.setColor(Color.GRAY);
					g.fillRect(p.x, p.y, Common.squaresize - Common.delim,
							Common.squaresize - Common.delim);
				}
			}
		}
	}

	/**
	 * Megadja, hogy kezdhetünk-e éppen új fal építésébe.
	 * 
	 * Csak akkor építhetünk új falat, ha éppen nincs egy sem épülõben.
	 * 
	 * @return igaz, ha építhetünk
	 */
	public boolean freeToBulid() {
		return (objects.size() == balls);
	}

	/**
	 * Csökkenti a hátralévõ idõt.
	 */
	public void decrementTime() {
		time--;
	}

	/**
	 * Az életek számát String formában megadó függvény.
	 * 
	 * @return a hátralévõ életek száma (String)
	 */
	public String getLives() {
		return Integer.toString(lives);
	}

	/**
	 * Az életek számát megadó függvény.
	 * 
	 * @return a hátralévõ életek száma
	 */
	public int getLivesNumber() {
		return lives;
	}

	/**
	 * A pálya telítettségét százalékban megadó függvény.
	 * 
	 * @return a pálya telítettsége százalékban
	 */
	public String getPercent() {
		return Integer.toString(board.getPercent());
	}

	/**
	 * A hátralévõ idõt String formában visszaadó függvény.
	 * 
	 * @return a hátralévõ idõ (String)
	 */
	public String getTime() {
		return Integer.toString(time);
	}

	/**
	 * A hátralévõ idõt megadó függvény.
	 * 
	 * @return a hátralévõ idõ
	 */
	public int getTimeNumber() {
		return time;
	}

	/**
	 * A pontjaink számát megadó függvény.
	 * 
	 * @return a pontjaink száma
	 */
	public String getPoints() {
		return Integer.toString(points);
	}
}
