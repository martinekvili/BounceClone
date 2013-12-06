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
 * A j�t�kot reprezent�l� oszt�ly.
 */
public class Game {

	/**
	 * A j�t�k �llapotai.
	 */
	public enum GameState {
		/**
		 * �ppen fut.
		 */
		RUNNING,

		/**
		 * Sz�netel.
		 */
		PAUSED,

		/**
		 * A j�t�k v�get �rt, vesztett�nk.
		 */
		OVER,

		/**
		 * A j�t�k v�get �rt, nyert�nk.
		 */
		WON
	}

	/**
	 * A h�tral�v� id�, m�sodpercekben.
	 */
	private int time;

	/**
	 * A pontjaink sz�ma.
	 */
	private int points;

	/**
	 * A megl�v� �letek sz�ma.
	 */
	int lives;

	/**
	 * A p�ly�n l�v� labd�k sz�ma.
	 */
	int balls;

	/**
	 * A p�lya.
	 */
	public Board board;

	/**
	 * A j�t�k aktu�lis �llapota.
	 */
	public GameState state;

	/**
	 * A j�t�kban l�v� objektumok list�ja.
	 */
	List<GameObject> objects;

	/**
	 * Konstruktor, ami a szint alapj�n be�ll�t minden �rt�ket.
	 * 
	 * Be�ll�tja az id�t, az �letek sz�m�t, l�trehoz megfelel� mennyis�g�
	 * labd�t, inicializ�lja a p�ly�t �s v�g�l fut� �llapotba �ll�tja �nmag�t.
	 * 
	 * @param level
	 *            - az aktu�lis szint
	 * @param points
	 *            - a m�r megl�v� pontjaink sz�ma
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
	 * F�ggv�ny, amellyel �jabb objektumot adhatunk a j�t�khoz.
	 * 
	 * @param o
	 *            - az �j objektum
	 */
	public void addObject(GameObject o) {
		objects.add(o);
	}

	/**
	 * L�ptet� f�ggv�ny.
	 * 
	 * V�gigmegy a j�t�k �sszes objektum�n, �s l�pteti �ket, majd ellen�rizteti
	 * az esetleges �tk�z�seket. Ezek ut�n kit�rli a m�r sz�ks�gtelen
	 * objektumokat. A v�g�n ellen�rzi, hogy nyert�nk avagy vesz�tett�nk-e, �s
	 * ha igen, akkor ennek megfelel�en be�ll�tja a j�t�k �llapot�t.
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
	 * Kirajzolja a j�t�kban tal�lhat� objektumokat.
	 * 
	 * @param g
	 *            - a Graphics amire rajzolunk
	 */
	public void paintObjects(Graphics g) {
		for (GameObject o : objects)
			o.paint(g);
	}

	/**
	 * Kirajzolja a p�ly�t.
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
	 * Megadja, hogy kezdhet�nk-e �ppen �j fal �p�t�s�be.
	 * 
	 * Csak akkor �p�thet�nk �j falat, ha �ppen nincs egy sem �p�l�ben.
	 * 
	 * @return igaz, ha �p�thet�nk
	 */
	public boolean freeToBulid() {
		return (objects.size() == balls);
	}

	/**
	 * Cs�kkenti a h�tral�v� id�t.
	 */
	public void decrementTime() {
		time--;
	}

	/**
	 * Az �letek sz�m�t String form�ban megad� f�ggv�ny.
	 * 
	 * @return a h�tral�v� �letek sz�ma (String)
	 */
	public String getLives() {
		return Integer.toString(lives);
	}

	/**
	 * Az �letek sz�m�t megad� f�ggv�ny.
	 * 
	 * @return a h�tral�v� �letek sz�ma
	 */
	public int getLivesNumber() {
		return lives;
	}

	/**
	 * A p�lya tel�tetts�g�t sz�zal�kban megad� f�ggv�ny.
	 * 
	 * @return a p�lya tel�tetts�ge sz�zal�kban
	 */
	public String getPercent() {
		return Integer.toString(board.getPercent());
	}

	/**
	 * A h�tral�v� id�t String form�ban visszaad� f�ggv�ny.
	 * 
	 * @return a h�tral�v� id� (String)
	 */
	public String getTime() {
		return Integer.toString(time);
	}

	/**
	 * A h�tral�v� id�t megad� f�ggv�ny.
	 * 
	 * @return a h�tral�v� id�
	 */
	public int getTimeNumber() {
		return time;
	}

	/**
	 * A pontjaink sz�m�t megad� f�ggv�ny.
	 * 
	 * @return a pontjaink sz�ma
	 */
	public String getPoints() {
		return Integer.toString(points);
	}
}
