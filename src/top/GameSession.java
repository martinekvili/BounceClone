package top;

import game.Game;
import game.Game.GameState;

import javax.swing.JOptionPane;

import view.GameFrame;
import control.FrameGenerator;

public class GameSession {

	private int gamenumber;
	private GameFrame view;
	private boolean over;
	private String name;
	private FrameGenerator framegen;

	public GameSession(GameFrame f) {
		gamenumber = 0;
		view = f;
		over = false;
	}

	public void game() {
		while (!over) {
			view.setGameSession(this);
			Game game = new Game(gamenumber);
			view.setGame(game);
			framegen = new FrameGenerator(game, view.getGamePanel());
			framegen.start();
			try {
				framegen.join();
			} catch (InterruptedException e) {
			}

			if (game.state == GameState.OVER) {
				over = true;
				name = JOptionPane.showInputDialog(view, "What's your name?",
						"Question", JOptionPane.QUESTION_MESSAGE);
				if (name != null && !name.equals(""))
					System.out.println(name);
			} else {
				JOptionPane.showMessageDialog(view,
						"Congratz, you have done level " + (gamenumber + 1)
								+ "!", "Hey!", JOptionPane.INFORMATION_MESSAGE);
				gamenumber++;
			}
		}
	}

	public void pause() {
		framegen.playPause();
		JOptionPane.showMessageDialog(view, "Game paused.", "Hey!",
				JOptionPane.INFORMATION_MESSAGE);
		framegen.playPause();
	}
}
