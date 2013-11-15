package top;

import game.Game;
import game.Game.Stat;
import view.GameFrame;
import control.FrameGenerator;

public class GameSession {

	private int gamenumber;
	private GameFrame view;
	private boolean over;

	public GameSession(GameFrame f) {
		gamenumber = 0;
		view = f;
		over = false;
	}

	public void game() {
		while (!over) {
			Game game = new Game(gamenumber);
			view.setGame(game);
			FrameGenerator framegen = new FrameGenerator(game,
					view.getGamePanel());
			view.setFrameGenerator(framegen);
			framegen.start();
			try {
				framegen.join();
			} catch (InterruptedException e) {
			}

			if (game.state == Stat.OVER)
				over = true;
			else
				gamenumber++;
		}
	}

}
