package top;

import javax.swing.JOptionPane;

import game.Game;
import game.Game.GameState;
import view.GameFrame;
import view.NameInputFrame;
import control.FrameGenerator;
import control.NameListener;

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
			framegen = new FrameGenerator(game,
					view.getGamePanel());
			view.setFrameGenerator(framegen);
			framegen.start();
			try {
				framegen.join();
			} catch (InterruptedException e) {
			}

			if (game.state == GameState.OVER) {
				over = true;
				
				// System.out.println(name);
			} else {
				JOptionPane.showMessageDialog(view,
						"Congratz, you have done level " + (gamenumber + 1)
								+ "!", "Hey!", JOptionPane.INFORMATION_MESSAGE);
				gamenumber++;
			}
		}
	}

	public void setName(String n) {
		name = n;
	}

	public void pause() {
		framegen.playPause();
		JOptionPane.showMessageDialog(view,
				"Game paused.", "Hey!", JOptionPane.INFORMATION_MESSAGE);
		framegen.playPause();
	}
}
