package view;

import game.Game;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import top.Common;
import top.GameSession;
import control.MenuListener;
import control.WindowListener;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private GamePanel gamepanel;
	private MenuPanel menupanel;
	private ScorePanel scorepanel;

	private GameSession gamesession;

	private CardLayout layout;
	private JPanel window;

	// private boolean ismenu;

	// public JPanel getWindow() {
	// return window;
	// }

	public GameFrame() {
		addWindowListener(new WindowListener());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Common.width + 6, Common.height + 55);
		setResizable(false);
		setTitle("BounceClone");
		setLocation(100, 100);

		MenuListener listener = new MenuListener(this);

		window = new JPanel();

		layout = new CardLayout();
		window.setLayout(layout);

		gamepanel = new GamePanel();
		menupanel = new MenuPanel(listener);
		scorepanel = new ScorePanel(listener);

		window.add(menupanel, "menu");
		window.add(gamepanel, "game");
		window.add(scorepanel, "scores");

		// ismenu = true;
		show("menu");
		// show("game");

		add(window, BorderLayout.CENTER);

		gamesession = null;
	}

	public void setGame(Game g) {
		gamepanel.setGame(g);
	}

	public GamePanel getGamePanel() {
		return gamepanel;
	}

	public void show(String s) {
		if (s.equals("menu"))
			menupanel.setContinue(hasGameSession());

		layout.show(window, s);
	}

	public boolean hasGameSession() {
		return (gamesession != null);
	}

	public void setGameSession(GameSession gameSession) {
		gamepanel.setGameSession(gameSession);
		gamesession = gameSession;
	}

	public GameSession getGameSession() {
		return gamesession;
	}
}
