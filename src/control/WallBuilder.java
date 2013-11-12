package control;

import game.Board.State;
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
			if (game.freeToBulid()) {
				BoardPos pos1 = BoardPos
						.vecToPos(new Vector(e.getX(), e.getY()));
				BoardPos pos2 = new BoardPos(pos1.xpos, pos1.ypos);
				if (dir == Orientation.HORIZONTAL) {
					pos1.xpos = (int) Math.round((double) e.getX()
							/ Common.squaresize);
					pos2.xpos = pos1.xpos - 1;
				} else {
					pos1.ypos = (int) Math.round((double) e.getY()
							/ Common.squaresize);
					pos2.ypos = pos1.ypos - 1;
				}

				if (pos1.xpos < Common.boardwidth
						&& pos1.ypos < Common.boardheight
						&& game.board.getState(pos1) != State.WALL)
					game.addObject(new HalfDoneWall(game, pos1, dir,
							Direction.POSITIVE));

				if (pos2.xpos >= 0 && pos2.ypos >= 0
						&& game.board.getState(pos2) != State.WALL)
					game.addObject(new HalfDoneWall(game, pos2, dir,
							Direction.NEGATIVE));
			}
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
