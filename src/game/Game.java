package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Game {
	
	int time;
	int lives;
	Board board;
	
	private List<GameObject> objects;
	
	public Game (int level) {
		
		time = 90 + level * 30;
		lives = 2 + level;
		
		board = new Board();
		
		objects = new ArrayList<GameObject>();
		
		for (int i = 0; i < lives; i++)
			objects.add(new Ball());
		
	}
	
	public void step() {
		for (GameObject o : objects)
			o.step();
	}
	
	public void paint (Graphics g) {
		for (GameObject o : objects)
			o.paint(g);
	}

}
