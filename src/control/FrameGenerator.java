package control;

import game.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import top.Common;
import view.BoardView;

public class FrameGenerator implements ActionListener {

	private Timer timer;
	private Game game;
	private BoardView view;

	public FrameGenerator(Game g, BoardView v) {
		game = g;
		view = v;
		timer = new Timer(1000 / Common.framerate, this);
	}

	public void start() {
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	public void actionPerformed(ActionEvent arg0) {
		game.step();
		view.repaint();
	}

}
