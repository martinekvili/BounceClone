package view;

import game.Game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import top.Common;
import control.WallBuilder;

public class BoardView extends JPanel {

	private static final long serialVersionUID = 1L;

	Game game;

	public BoardView(Game g) {
		game = g;
		addMouseListener(new WallBuilder(game));
		setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(Common.width, Common.height));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		game.paintObjects(g);
		game.paintBoard(g);

	}

}
