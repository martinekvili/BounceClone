package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import top.GameSession;
import view.GameFrame;

public class MenuListener implements ActionListener {

	private GameFrame gameframe;

	public MenuListener(GameFrame gf) {
		gameframe = gf;
	}

	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "game":
			gameframe.show("game");
			if (gameframe.hasGameSession()) {
				gameframe.getGameSession().halt();
			}
			GameSession session = new GameSession(gameframe);
			session.start();

			// gameframe.repaint();
			break;

		case "scores":
			gameframe.show("scores");
			break;

		case "continue":
			gameframe.getGameSession().play();
			gameframe.show("game");

			break;

		case "quit":
			gameframe.dispatchEvent(new WindowEvent(gameframe,
					WindowEvent.WINDOW_CLOSING));
			break;

		case "back":
			gameframe.show("menu");
			break;

		default:
			break;
		}
	}

}
