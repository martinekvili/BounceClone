package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import top.GameSession;
import view.GameFrame;

/**
 * A gombnyom�sokat figyel� oszt�ly.
 */
public class ButtonListener implements ActionListener {

	/**
	 * Az ablak, amiben a gombnyom�sok t�rt�nnek, �s amiben a v�ltoz�sok
	 * t�rt�nnek ezek hat�s�ra.
	 */
	private GameFrame gameframe;

	
	/**
	 * Konstruktor.
	 * 
	 * @param gameframe - az ablak, amire r��ll�tjuk az oszt�lyt
	 */
	public ButtonListener(GameFrame gameframe) {
		this.gameframe = gameframe;
	}

	/**
	 * A f�ggv�ny ami gombnyom�skor h�v�dik.
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
