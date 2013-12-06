package control;

import game.Game;
import game.Game.GameState;
import top.Common;
import view.GamePanel;

/**
 * A játékot léptetõ osztály.
 * 
 * Önálló szálként fut, szüneteltethetõ és végleg leállítható kívülrõl is, de ha
 * a játéknak vége, akkor is leáll.
 */
public class FrameGenerator extends Thread {

	/**
	 * A játék amit léptet.
	 */
	private Game game;

	/**
	 * A megjelenítõ, amit frissítget.
	 */
	private GamePanel view;

	/**
	 * A frame-k közti várakozási idõ, ezredmásodpercben.
	 */
	private int waittime;

	/**
	 * A szüneteltetéshez szükséges változó.
	 */
	private volatile boolean paused;

	/**
	 * A leállításhoz szükséges változó.
	 */
	private volatile boolean stopped;

	/**
	 * Számlálja, hogy hány frame-t jeleztünk már ki.
	 * 
	 * Az idõmérésben van jelentõsége, mivel a kijelzett framek száma, és a
	 * framerate alapján mérjük az idõt.
	 */
	private long frames;

	/**
	 * Az osztály konstruktora.
	 * 
	 * @param game
	 *            - a léptetendõ játék
	 * @param view
	 *            - a frissítendõ megjelenítõ
	 */
	public FrameGenerator(Game game, GamePanel view) {
		this.game = game;
		this.view = view;
		waittime = 1000 / Common.framerate;

		paused = false;
		stopped = false;
		frames = 0;
	}

	/**
	 * A szüneteltetõ / újra elindító függvény.
	 */
	public void playPause() {
		paused = !paused;
	}

	/**
	 * A végleges leállítást végzõ függvény.
	 */
	public void halt() {
		stopped = true;
	}

	/**
	 * Maga a léptetést végzõ ciklus.
	 * 
	 * Akkor áll le, ha vége a játéknak, vagy kívülrõl leállítottuk. Amikor nem
	 * szünetel, akkor lépteti a játékot, és frissíti a megjelenítõt. Két lépés
	 * közt a Thread.sleep() metódus segítségével várakozik.
	 */
	public void run() {
		while (game.state != GameState.OVER && game.state != GameState.WON
				&& !stopped) {
			if (!paused) {
				frames++;

				if (frames % Common.framerate == 0)
					game.decrementTime();

				game.step();
				view.refresh();

				try {
					sleep(waittime);
				} catch (InterruptedException e) {
				}
			}
		}
	}

}
