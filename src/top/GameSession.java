package top;

import game.Game;
import game.Game.GameState;

import javax.swing.JOptionPane;

import view.GameFrame;
import control.FrameGenerator;

public class GameSession extends Thread {

	private int gamenumber;
	private int points;
	private GameFrame view;
	private volatile boolean over;
	private String name;
	private FrameGenerator framegen;

	public GameSession(GameFrame f) {
		gamenumber = 0;
		points = 0;
		view = f;
		over = false;
	}

	public void run() {
		game();
	}

	public void game() {
		while (!over) {
			view.setGameSession(this);
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
				name = JOptionPane.showInputDialog(view, "What's your name?",
						"Game Over", JOptionPane.QUESTION_MESSAGE);

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

	public void play() {
		framegen.playPause();
	}

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

	public void halt() {
		over = true;
		framegen.halt();
	}
}
