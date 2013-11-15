package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseListener implements ActionListener {
	
	private FrameGenerator framegen;

	public void actionPerformed(ActionEvent arg0) {
		framegen.playPause();
	}
	
	public void setFrameGenerator(FrameGenerator fg) {
		framegen = fg;
	}

}
