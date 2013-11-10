package game;

import game.Board.State;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import top.Common;
import view.Place;

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
	
	public void paintObjects (Graphics g) {
		for (GameObject o : objects)
			o.paint(g);
	}
	
	public void paintBoard (Graphics g) {
		for (int i = -1; i < Common.boardheight + 1; i++) {
			for (int j = -1; j < Common.boardwidth + 1; j++) {
				BoardPos pos = new BoardPos(j, i);
				if (board.getState(pos) == State.WALL) {
					Place p = Place.posToPlace(pos);
					g.fillRect(p.x, p.y, Common.squaresize, Common.squaresize);
				}
			}
		}
	}

}
