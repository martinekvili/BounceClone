package top;

import game.Game;
import game.Game.GameState;

import javax.swing.JOptionPane;

import view.GameFrame;
import control.FrameGenerator;

/**
 * Mag�t a j�t�kot menedzsel� oszt�ly.
 * 
 * �n�ll� sz�lk�nt fut, le�ll ha v�ge a j�t�knak, de k�v�lr�l is le�ll�that�.
 */
public class GameSession extends Thread {

	/**
	 * Az el�rt szint sz�ma.
	 */
	private int gamenumber;

	/**
	 * A megl�v� pontjaink sz�ma.
	 */
	private int points;

	/**
	 * A megjelen�t� ablak.
	 */
	private GameFrame view;

	/**
	 * A sz�l le�ll�t�s�hoz sz�ks�ges v�ltoz�.
	 */
	private volatile boolean over;

	/**
	 * Az aktu�lis j�t�kot l�ptetget� FrameGenerator.
	 */
	private FrameGenerator framegen;

	/**
	 * Konstruktor.
	 * 
	 * Be�ll�tja a megadott megjelen�t�nek �nmag�t j�t�kvez�rl�k�nt.
	 * 
	 * @param frame
	 *            - a megjelen�t� ablak
	 */
	public GameSession(GameFrame frame) {
		gamenumber = 0;
		points = 0;
		view = frame;
		over = false;

		view.setGameSession(this);
	}

	/**
	 * Mag�t a j�t�kot vez�rl� f�ggv�ny.
	 * 
	 * Am�g le nem �ll�tjuk k�v�lr�l vagy el nem vesz�tj�k valamelyik j�t�kot,
	 * addig v�gigv�rja, hogy v�gig�rjen az adott menet, siker eset�n gratul�l,
	 * megadja a meg�rdemelt pontokat �s ind�tja a k�vetkez�t, vesz�t�s eset�n
	 * pedig bek�ri a j�t�kos nev�t, �s elmenti a dics�s�gist�ra az el�rt
	 * pontjai sz�m�val egy�tt.
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
	 * A sz�neteltet�sb�l �jra elind�t� f�ggv�ny.
	 * 
	 * �jra elind�tja a l�ptetget� FrameGeneratort.
	 */
	public void play() {
		framegen.playPause();
	}

	/**
	 * A sz�netet megval�s�t� f�ggv�ny.
	 * 
	 * Sz�net eset�n le�ll�tja a j�t�k l�ptetget�s�t, �s megk�rdezi a j�t�kost,
	 * hogy mit szeretne tenni: folytatni a j�t�kot, avagy visszat�rni a men�be.
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
	 * A j�t�k k�ls� le�ll�t�s�t v�gz� f�ggv�ny.
	 * 
	 * Le�ll�tja az aktu�lis menetet, �s jelzi �nmag�nak is, hogy itt a v�g.
	 */
	public void halt() {
		over = true;
		framegen.halt();
	}
}
