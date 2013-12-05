package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import control.MenuListener;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton cont;

	public MenuPanel(MenuListener listener) {
		JPanel top = new JPanel();

		JButton game = new JButton("Start game");
		game.setActionCommand("game");
		game.addActionListener(listener);

		cont = new JButton("Continue");
		cont.setActionCommand("continue");
		cont.addActionListener(listener);

		JButton highscores = new JButton("Highscores");
		highscores.setActionCommand("scores");
		highscores.addActionListener(listener);

		JButton quit = new JButton("Quit");
		quit.setActionCommand("quit");
		quit.addActionListener(listener);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));

		panel.add(game);
		panel.add(cont);
		panel.add(highscores);
		panel.add(quit);

		panel.setPreferredSize(new Dimension(300, 150));
		top.add(panel);

		// setLayout(new BorderLayout());
		add(top);
	}

	public void setContinue(boolean enabled) {
		cont.setEnabled(enabled);
	}

}
