package control;

import game.Game;
import top.Common;
import view.BoardView;

public class FrameGenerator extends Thread {

	private Game game;
	private BoardView view;
	private int waittime;

	public volatile boolean running;
	public volatile boolean paused;

	private long frames;
	private long time;

	public FrameGenerator(Game g, BoardView v) {
		game = g;
		view = v;
		waittime = 1000 / Common.framerate;
		running = true;
		paused = false;

		frames = 0;
	}

	public void end() {
		running = false;
	}

	public void start() {
		time = System.currentTimeMillis();
		super.start();
	}

	public void run() {
		while (running) {
			if (!paused) {
				frames++;
				game.step();
				view.repaint();
				try {
					sleep(waittime);
				} catch (InterruptedException e) {
				}
				System.out.println((double) frames
						/ (System.currentTimeMillis() - time) * 1000);
			}
		}
	}

}
