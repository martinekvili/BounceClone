package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import control.MenuListener;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public MenuPanel(GameFrame gf) {
		MenuListener listener = new MenuListener(gf);
		
		JButton game = new JButton("Start game");
		game.setActionCommand("game");
		game.addActionListener(listener);
		
		JButton cont = new JButton("Continue");
		cont.setActionCommand("continue");
		cont.addActionListener(listener);
		
		JButton highscores = new JButton("Highscores");
		highscores.setActionCommand("highscores");
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
		
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
	}

}
