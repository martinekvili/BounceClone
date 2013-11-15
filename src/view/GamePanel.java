package view;

import game.Game;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private BoardView board;
	private JLabel lives;
	private JLabel percent;
	private JLabel time;

	public GamePanel(Game g) {
		setLayout(new BorderLayout());

		board = new BoardView(g);

		add(board, BorderLayout.CENTER);

		JPanel bottom = new JPanel();
		bottom.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;

		JButton back = new JButton("Back to Menu");

		c.gridwidth = 2;
		bottom.add(back, c);

		c.gridwidth = 1;
		bottom.add(new JLabel(), c);

		lives = new JLabel();
		percent = new JLabel();
		time = new JLabel();

		bottom.add(new JLabel("Lives:"), c);
		bottom.add(lives, c);
		bottom.add(new JLabel("Time:"), c);
		bottom.add(time, c);
		bottom.add(new JLabel("Percent:"), c);
		bottom.add(percent, c);

		add(bottom, BorderLayout.SOUTH);
	}

	public void refresh() {
		board.repaint();
		lives.setText(board.game.getLives());
		percent.setText(board.game.getPercent());
		time.setText(board.game.getTime());
	}

}
