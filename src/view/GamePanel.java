package view;

import game.Game;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ButtonListener;

/**
 * A játékhoz szükséges információkat tartalmazó JPanel.
 */
public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * A játékot megjelenítõ BoardView.
	 */
	private BoardView board;

	/**
	 * Az életek számának kijelzése.
	 */
	private JLabel lives;

	/**
	 * A pálya telítettségének kijelzése.
	 */
	private JLabel percent;

	/**
	 * A hátralévõ idõ kijelzése.
	 */
	private JLabel time;

	/**
	 * A pontjaink számának kijelzése.
	 */
	private JLabel points;

	/**
	 * Konstruktor.
	 * 
	 * Összerakja a megfelelõ kinézetet.
	 * 
	 * @param listener
	 *            - a gombnyomást figyelõ listener
	 */
	public GamePanel(ButtonListener listener) {
		setLayout(new BorderLayout());

		board = new BoardView();

		add(board, BorderLayout.CENTER);

		JPanel bottom = new JPanel();
		bottom.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;

		JButton pause = new JButton("Pause");
		pause.setActionCommand("pause");
		pause.addActionListener(listener);

		c.gridwidth = 1;
		bottom.add(pause, c);

		c.gridwidth = 2;
		c.weightx = 1;
		bottom.add(new JLabel(""), c);

		lives = new JLabel();
		percent = new JLabel();
		time = new JLabel();
		points = new JLabel();

		c.gridwidth = 1;
		c.weightx = 0.1;
		bottom.add(new JLabel("Lives:"), c);
		bottom.add(lives, c);
		bottom.add(new JLabel("Time:"), c);
		bottom.add(time, c);
		bottom.add(new JLabel("Percent:"), c);
		bottom.add(percent, c);
		bottom.add(new JLabel("Points:"), c);
		bottom.add(points, c);

		add(bottom, BorderLayout.SOUTH);
	}

	/**
	 * A kijelzés frissítését végzõ függvény.
	 * 
	 * Újrarajzolja a pályát, és újra lekéri a megfelelõ adatokat a játéktól
	 * (élet, idõ, stb.).
	 */
	public void refresh() {
		board.repaint();
		lives.setText(board.game.getLives());
		percent.setText(board.game.getPercent());
		time.setText(board.game.getTime());
		points.setText(board.game.getPoints());
	}

	/**
	 * Beállítja az aktuális játékot ennek a megjelenítõnek.
	 * 
	 * @param game
	 *            - az aktuális játék
	 */
	public void setGame(Game game) {
		board.setGame(game);
	}

}
