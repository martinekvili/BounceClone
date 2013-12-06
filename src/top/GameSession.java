package top;

import game.Game;
import game.Game.GameState;

import javax.swing.JOptionPane;

import view.GameFrame;
import control.FrameGenerator;

/**
 * Magát a játékot menedzselõ osztály.
 * 
 * Önálló szálként fut, leáll ha vége a játéknak, de kívülrõl is leállítható.
 */
public class GameSession extends Thread {

	/**
	 * Az elért szint száma.
	 */
	private int gamenumber;

	/**
	 * A meglévõ pontjaink száma.
	 */
	private int points;

	/**
	 * A megjelenítõ ablak.
	 */
	private GameFrame view;

	/**
	 * A szál leállításához szükséges változó.
	 */
	private volatile boolean over;

	/**
	 * Az aktuális játékot léptetgetõ FrameGenerator.
	 */
	private FrameGenerator framegen;

	/**
	 * Konstruktor.
	 * 
	 * Beállítja a megadott megjelenítõnek önmagát játékvezérlõként.
	 * 
	 * @param frame
	 *            - a megjelenítõ ablak
	 */
	public GameSession(GameFrame frame) {
		gamenumber = 0;
		points = 0;
		view = frame;
		over = false;

		view.setGameSession(this);
	}

	/**
	 * Magát a játékot vezérlõ függvény.
	 * 
	 * Amíg le nem állítjuk kívülrõl vagy el nem veszítjük valamelyik játékot,
	 * addig végigvárja, hogy végigérjen az adott menet, siker esetén gratulál,
	 * megadja a megérdemelt pontokat és indítja a következõt, veszítés esetén
	 * pedig bekéri a játékos nevét, és elmenti a dicsõségistára az elért
	 * pontjai számával együtt.
	 */
	public void run() {
		while (!over) {
			Game game = new Game(gamenumber, points);
			view.setGame(game);

			framegen = new FrameGenerator(game, view.getGamePanel());
			framegen.start();
			try {
				framegen.join();
			} catch (InterruptedException e) {
			}

			if (over) {
				view.setGameSession(null);
				break;
			}

			if (game.state == GameState.OVER) {
				over = true;
				String name = JOptionPane.showInputDialog(view,
						"What's your name?", "Game Over",
						JOptionPane.QUESTION_MESSAGE);

				if (name != null && !name.equals("")) {
					BounceClone.highscores.add(new Score(name, points));
				}

				view.setGameSession(null);
				view.show("menu");

			} else {
				JOptionPane.showMessageDialog(view,
						"Congratz, you have done level " + (gamenumber + 1)
								+ "!", "Hey!", JOptionPane.INFORMATION_MESSAGE);

				points += game.getTimeNumber();
				points += (game.getLivesNumber() - 1) * 60;

				gamenumber++;
			}
		}
	}

	/**
	 * A szüneteltetésbõl újra elindító függvény.
	 * 
	 * Újra elindítja a léptetgetõ FrameGeneratort.
	 */
	public void play() {
		framegen.playPause();
	}

	/**
	 * A szünetet megvalósító függvény.
	 * 
	 * Szünet esetén leállítja a játék léptetgetését, és megkérdezi a játékost,
	 * hogy mit szeretne tenni: folytatni a játékot, avagy visszatérni a menübe.
	 */
	public void pause() {
		framegen.playPause();

		Object[] options = { "Quit to Menu", "Continue" };

		int answer = JOptionPane.showOptionDialog(view,
				"What would you like to do?", "Game Paused",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[1]);

		switch (answer) {
		case 0:
			view.show("menu");
			break;
		case 1:
			framegen.playPause();
			break;
		}
	}

	/**
	 * A játék külsõ leállítását végzõ függvény.
	 * 
	 * Leállítja az aktuális menetet, és jelzi önmagának is, hogy itt a vég.
	 */
	public void halt() {
		over = true;
		framegen.halt();
	}
}
