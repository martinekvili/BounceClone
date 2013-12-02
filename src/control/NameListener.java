package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import top.GameSession;
import view.NameInputFrame;

public class NameListener implements ActionListener {

	private GameSession session;
	private NameInputFrame inputframe;
	
	public NameListener(GameSession gs) {
		session = gs;
	}
	
	public void setInputFrame(NameInputFrame nif) {
		inputframe = nif;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		session.setName(inputframe.getName());
	}

}
