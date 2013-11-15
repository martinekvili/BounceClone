package control;

import game.Game;
import game.Game.Stat;
import top.Common;
import view.GamePanel;

public class FrameGenerator extends Thread {

	private Game game;
	private GamePanel view;
	private int waittime;
	private volatile boolean paused;

	private long frames;
	private long time;

	public FrameGenerator(Game g, GamePanel v) {
		game = g;
		view = v;
		waittime = 1000 / Common.framerate;

		paused = false;
		frames = 0;
	}
	
	public void playPause() {
		paused = !paused;
	}

	public void start() {
		time = System.currentTimeMillis();
		super.start();
	}

	public void run() {
		while (game.state != Stat.OVER && game.state != Stat.WON) {
			if (!paused) {
				System.out.println(frames);
				frames++;

				if (frames % Common.framerate == 0)
					game.decrementTime();

				game.step();
				view.refresh();
				try {
					sleep(waittime);
				} catch (InterruptedException e) {
				}
				//System.out.println((double) frames
				//		/ (System.currentTimeMillis() - time) * 1000);
			}
		}
	}

}
