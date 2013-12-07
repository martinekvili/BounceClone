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
 * A j�t�k megjelen�t�s�hez sz�ks�ges oszt�ly.
 * 
 * Ebben deklar�ltuk fel�l a paintComponent f�ggv�nyt, hogy alacsony szint�
 * grafik�t tudjunk alkalmazni.
 */
public class BoardView extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * A j�t�k amit megjelen�t.
	 */
	Game game;

	/**
	 * Az eg�rkattint�sokat figyel� listener.
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
	 * Ezt a f�ggv�nyt kellett fel�ldeklar�lni, hogy tudjuk haszn�lni az
	 * alacsonyszint� grafik�t.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (game != null) {
			game.paintObjects(g);
			game.paintBoard(g);
		}
	}

	/**
	 * Be�ll�tja az aktu�lis j�t�kot ennek a megjelen�t�nek.
	 * 
	 * @param game
	 *            - az aktu�lis j�t�k.
	 */
	public void setGame(Game game) {
		listener.setGame(game);
		this.game = game;
	}

}
