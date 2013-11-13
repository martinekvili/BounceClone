package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import top.Common;
import view.Place;

public class Game {

	private int time;
	int lives;
	int balls;
	public Board board;

	List<GameObject> objects;

	public Game(int level) {

		time = 90 + level * 30;
		lives = 2 + level;
		balls = 2 + level;

		board = new Board(this);

		objects = new ArrayList<GameObject>();

		for (int i = 0; i < balls; i++)
			objects.add(new Ball(this));
	}

	public void addObject(GameObject o) {
		objects.add(o);
	}

	public void step() {
		for (GameObject o : objects) {
			o.step();
			o.collide();
		}

		Iterator<GameObject> i = objects.iterator();
		while (i.hasNext()) {
			if (i.next().isRemoveable())
				i.remove();
		}

		// System.out.println(board.getPercent());
	}

	public void paintObjects(Graphics g) {
		g.setColor(Color.BLUE);
		for (GameObject o : objects)
			o.paint(g);
	}

	public void paintBoard(Graphics g) {
		for (int i = 0; i < Common.boardheight; i++) {
			for (int j = 0; j < Common.boardwidth; j++) {
				BoardPos pos = new BoardPos(j, i);
				Place p = Place.posToPlace(pos);
				switch (board.getState(pos)) {

				case WALL:
					g.setColor(Color.GRAY);
					g.fillRect(p.x, p.y, Common.squaresize - Common.delim,
							Common.squaresize - Common.delim);
					break;

				// case UNDER_CONSTRUCTION:
				// g.setColor(Color.YELLOW);
				// g.fillRect(p.x, p.y, Common.squaresize - Common.delim,
				// Common.squaresize - Common.delim);
				// break;

				default:
					break;
				}
			}
		}
	}

	public boolean freeToBulid() {
		return (objects.size() == balls);
	}
}
