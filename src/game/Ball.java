package game;

import java.awt.Graphics;
import java.util.Random;

import top.Common;

public class Ball implements GameObject {

	private Vector place;
	private Vector speed;

	public Ball() {
		Random rnd = new Random();

		place = new Vector(rnd.nextDouble() * Common.width, rnd.nextDouble()
				* Common.height);

		double angle = rnd.nextDouble() * 2 * Math.PI;
		speed = new Vector(Common.ballspeed * Math.cos(angle), Common.ballspeed
				* Math.sin(angle));

	}

	public void step() {
		place.add(speed);
		if (place.x < 0 || place.x > Common.width - Common.ballsize)
			speed.x *= -1;
		if (place.y < 0 || place.y > Common.height - Common.ballsize)
			speed.y *= -1;
		//System.out.println("xpos: " + place.x + ", ypos: " + place.y + ", xspeed: " + speed.x + ", yspeed: " + speed.y);
	}

	public void paint(Graphics g) {
		g.fillOval((int) place.x, (int) place.y, Common.ballsize, Common.ballsize);
	}

}
