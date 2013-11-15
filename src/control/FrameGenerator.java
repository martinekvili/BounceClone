package control;

import game.Game;
import top.Common;
import view.GamePanel;

public class FrameGenerator extends Thread {

	private Game game;
	private GamePanel view;
	private int waittime;

	public volatile boolean running;
	public volatile boolean paused;

	private long frames;
	private long time;

	public FrameGenerator(Game g, GamePanel v) {
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

				if (frames % Common.framerate == 0)
					game.decrementTime();

				game.step();
				view.refresh();
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
