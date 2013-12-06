package control;

import game.Game;
import game.Game.GameState;
import top.Common;
import view.GamePanel;

/**
 * A j�t�kot l�ptet� oszt�ly.
 * 
 * �n�ll� sz�lk�nt fut, sz�neteltethet� �s v�gleg le�ll�that� k�v�lr�l is, de ha
 * a j�t�knak v�ge, akkor is le�ll.
 */
public class FrameGenerator extends Thread {

	/**
	 * A j�t�k amit l�ptet.
	 */
	private Game game;

	/**
	 * A megjelen�t�, amit friss�tget.
	 */
	private GamePanel view;

	/**
	 * A frame-k k�zti v�rakoz�si id�, ezredm�sodpercben.
	 */
	private int waittime;

	/**
	 * A sz�neteltet�shez sz�ks�ges v�ltoz�.
	 */
	private volatile boolean paused;

	/**
	 * A le�ll�t�shoz sz�ks�ges v�ltoz�.
	 */
	private volatile boolean stopped;

	/**
	 * Sz�ml�lja, hogy h�ny frame-t jelezt�nk m�r ki.
	 * 
	 * Az id�m�r�sben van jelent�s�ge, mivel a kijelzett framek sz�ma, �s a
	 * framerate alapj�n m�rj�k az id�t.
	 */
	private long frames;

	/**
	 * Az oszt�ly konstruktora.
	 * 
	 * @param game
	 *            - a l�ptetend� j�t�k
	 * @param view
	 *            - a friss�tend� megjelen�t�
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
	 * A sz�neteltet� / �jra elind�t� f�ggv�ny.
	 */
	public void playPause() {
		paused = !paused;
	}

	/**
	 * A v�gleges le�ll�t�st v�gz� f�ggv�ny.
	 */
	public void halt() {
		stopped = true;
	}

	/**
	 * Maga a l�ptet�st v�gz� ciklus.
	 * 
	 * Akkor �ll le, ha v�ge a j�t�knak, vagy k�v�lr�l le�ll�tottuk. Amikor nem
	 * sz�netel, akkor l�pteti a j�t�kot, �s friss�ti a megjelen�t�t. K�t l�p�s
	 * k�zt a Thread.sleep() met�dus seg�ts�g�vel v�rakozik.
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
