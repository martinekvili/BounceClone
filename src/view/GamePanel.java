package view;

import game.Game;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import top.GameSession;
import control.FrameGenerator;
import control.PauseListener;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private BoardView board;
	private JLabel lives;
	private JLabel percent;
	private JLabel time;
	
	private PauseListener pauselistener;

	public GamePanel() {
		setLayout(new BorderLayout());

		board = new BoardView();
		pauselistener = new PauseListener();

		add(board, BorderLayout.CENTER);

		JPanel bottom = new JPanel();
		bottom.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;

		JButton pause = new JButton("Pause");
		pause.addActionListener(pauselistener);

		c.gridwidth = 1;
		bottom.add(pause, c);

		c.gridwidth = 2;
		c.weightx = 1;
		bottom.add(new JLabel(""), c);

		lives = new JLabel();
		percent = new JLabel();
		time = new JLabel();

		c.gridwidth = 1;
		c.weightx = 0.1;
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

	public void setGame(Game g) {
		board.setGame(g);
	}

	public void setFrameGenerator(FrameGenerator fg) {
		pauselistener.setFrameGenerator(fg);
	}

	public void setGameSession(GameSession gameSession) {
		pauselistener.setGamesession(gameSession);
	}

}
