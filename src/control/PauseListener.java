package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import top.GameSession;

public class PauseListener implements ActionListener {
	
	private FrameGenerator framegen;
	private GameSession gamesession;

	public void actionPerformed(ActionEvent arg0) {
		gamesession.pause();
		//framegen.playPause();
	}
	
	public void setFrameGenerator(FrameGenerator fg) {
		framegen = fg;
	}

	public void setGamesession(GameSession gamesession) {
		this.gamesession = gamesession;
	}

}
