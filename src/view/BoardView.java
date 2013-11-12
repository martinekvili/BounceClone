package view;

import game.Game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.JPanel;

import top.Common;
import control.WallBuilder;

public class BoardView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Game game;

	public BoardView(Game g) {
		game = g;
		addMouseListener(new WallBuilder(game));
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

}
