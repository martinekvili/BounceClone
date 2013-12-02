package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import top.GameSession;

public class PauseListener implements ActionListener {

	private GameSession gamesession;

	public void actionPerformed(ActionEvent arg0) {
		gamesession.pause();
	}

	public void setGamesession(GameSession gamesession) {
		this.gamesession = gamesession;
	}

}
