package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import top.GameSession;
import view.GameFrame;

public class MenuListener implements ActionListener {
	
	private GameFrame gameframe;
	
	public MenuListener(GameFrame gf) {
		gameframe = gf;
	}

	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand()) {
		case "game":
			gameframe.change();
			GameSession session = new GameSession(gameframe);
			session.game();
			
			//gameframe.repaint();
			break;
			
		case "continue":
			break;
			
		case "quit":
			break;
		
		default:
			break;
		}
	}

}
