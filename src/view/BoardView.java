package view;

import game.Board.State;
import game.BoardPos;
import game.Game;
import game.HalfDoneWall.Orientation;
import game.Vector;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import top.Common;

public class BoardView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Game game;

	public BoardView(Game g) {
		game = g;
		addMouseListener(new WallBuilder());
		setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// g.setColor(Color.BLACK);
		// g.fillRect(0, 0, 900, 600);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Common.width, Common.height);
		game.paintObjects(g);
		game.paintBoard(g);

	}
	
	private class WallBuilder implements MouseListener {
		
		private Orientation dir;
		private Cursor vertical, horizontal;
		
		public WallBuilder() {
			dir = Orientation.VERTICAL;
			vertical = new Cursor(Cursor.N_RESIZE_CURSOR);
			horizontal = new Cursor(Cursor.E_RESIZE_CURSOR);
		}

		public void mouseClicked(MouseEvent e) {
			switch(e.getButton()) {
			
			case 1:
				/* left mouse button */
				//int xpos = (int) Math.round((double)e.getX() / Common.squaresize);
				//int ypos = (int) Math.round((double)e.getY() / Common.squaresize);
				//BoardPos pos = new BoardPos(xpos, ypos);
				
				BoardPos pos = BoardPos.vecToPos(new Vector(e.getX(), e.getY()));
				
				game.board.setState(pos, State.WALL);
				break;
				
			case 3:
				/* right mouse button */
				if (dir == Orientation.VERTICAL) {
					dir = Orientation.HORIZONTAL;
					e.getComponent().setCursor(horizontal);
				}
				else {
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

		public void mouseReleased(MouseEvent e) {
		}
		
	}

}
