package view;

import game.Game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.JPanel;

import control.WallBuilder;

public class BoardView extends JPanel {

	private static final long serialVersionUID = 1L;

	private Game game;

	public BoardView(Game g) {
		game = g;
		addMouseListener(new WallBuilder(game));
		setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
		setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		game.paintObjects(g);
		game.paintBoard(g);

	}

}
