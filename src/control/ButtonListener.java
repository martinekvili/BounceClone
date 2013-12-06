package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import top.GameSession;
import view.GameFrame;

/**
 * A gombnyomásokat figyelõ osztály.
 */
public class ButtonListener implements ActionListener {

	/**
	 * Az ablak, amiben a gombnyomások történnek, és amiben a változások
	 * történnek ezek hatására.
	 */
	private GameFrame gameframe;

	
	/**
	 * Konstruktor.
	 * 
	 * @param gameframe - az ablak, amire ráállítjuk az osztályt
	 */
	public ButtonListener(GameFrame gameframe) {
		this.gameframe = gameframe;
	}

	/**
	 * A függvény ami gombnyomáskor hívódik.
	 */
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "game":
			gameframe.show("game");
			
			if (gameframe.hasGameSession()) {
				gameframe.getGameSession().halt();
			}
			
			GameSession session = new GameSession(gameframe);
			session.start();
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
			
		case "pause":
			gameframe.getGameSession().pause();

		default:
			break;
		}
	}

}
