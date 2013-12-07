package view;

import game.Game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import top.Common;
import control.WallBuilder;

/**
 * A játék megjelenítéséhez szükséges osztály.
 * 
 * Ebben deklaráltuk felül a paintComponent függvényt, hogy alacsony szintû
 * grafikát tudjunk alkalmazni.
 */
public class BoardView extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * A játék amit megjelenít.
	 */
	Game game;

	/**
	 * Az egérkattintásokat figyelõ listener.
	 */
	private WallBuilder listener;

	/**
	 * Konstruktor.
	 */
	public BoardView() {
		listener = new WallBuilder(game);
		addMouseListener(listener);
		setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(Common.width, Common.height));
	}

	/**
	 * Ezt a függvényt kellett felüldeklarálni, hogy tudjuk használni az
	 * alacsonyszintû grafikát.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (game != null) {
			game.paintObjects(g);
			game.paintBoard(g);
		}
	}

	/**
	 * Beállítja az aktuális játékot ennek a megjelenítõnek.
	 * 
	 * @param game
	 *            - az aktuális játék.
	 */
	public void setGame(Game game) {
		listener.setGame(game);
		this.game = game;
	}

}
