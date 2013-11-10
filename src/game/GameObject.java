package game;

import java.awt.Graphics;

public interface GameObject {
	
	public abstract void step();
	
	public abstract void paint(Graphics g);

}
