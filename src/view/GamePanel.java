package view;

import game.Game;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ButtonListener;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private BoardView board;
	private JLabel lives;
	private JLabel percent;
	private JLabel time;
	private JLabel points;

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

	public void refresh() {
		board.repaint();
		lives.setText(board.game.getLives());
		percent.setText(board.game.getPercent());
		time.setText(board.game.getTime());
		points.setText(board.game.getPoints());
	}

	public void setGame(Game g) {
		board.setGame(g);
	}

}
