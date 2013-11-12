package control;

import game.BoardPos;
import game.Game;
import game.HalfDoneWall;
import game.HalfDoneWall.Direction;
import game.HalfDoneWall.Orientation;
import game.Vector;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import top.Common;

public class WallBuilder implements MouseListener {

	private Orientation dir;
	private Cursor vertical, horizontal;
	private Game game;

	public WallBuilder(Game g) {
		dir = Orientation.VERTICAL;
		vertical = new Cursor(Cursor.N_RESIZE_CURSOR);
		horizontal = new Cursor(Cursor.E_RESIZE_CURSOR);
		game = g;
	}

	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {

		case 1:
			/* left mouse button */
			BoardPos pos = BoardPos.vecToPos(new Vector(e.getX(), e.getY()));
			if (dir == Orientation.HORIZONTAL)
				pos.xpos = (int) Math.round((double) e.getX()
						/ Common.squaresize);
			else
				pos.ypos = (int) Math.round((double) e.getY()
						/ Common.squaresize);

			game.addObject(new HalfDoneWall(game, pos, dir, Direction.POSITIVE));

			if (dir == Orientation.HORIZONTAL)
				pos.xpos = pos.xpos - 1;
			else
				pos.ypos = pos.ypos - 1;

			game.addObject(new HalfDoneWall(game, pos, dir, Direction.NEGATIVE));
			break;

		case 3:
			/* right mouse button */
			if (dir == Orientation.VERTICAL) {
				dir = Orientation.HORIZONTAL;
				e.getComponent().setCursor(horizontal);
			} else {
				dir = Orientation.VERTICAL;
				e.getComponent().setCursor(vertical);
			}

			break;

		default:
			break;
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

}
