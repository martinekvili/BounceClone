package view;

import game.Game;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import top.Common;
import top.GameSession;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private GamePanel gamepanel;
	private MenuPanel menupanel;

	private CardLayout layout;
	private JPanel window;
	private boolean ismenu;

	// public JPanel getWindow() {
	// return window;
	// }

	public GameFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Common.width + 6, Common.height + 55);
		setResizable(false);
		setTitle("BounceClone");
		setLocation(100, 100);

		window = new JPanel();

		layout = new CardLayout();
		window.setLayout(layout);

		gamepanel = new GamePanel();
		menupanel = new MenuPanel(this);

		window.add(menupanel, "menu");
		window.add(gamepanel, "game");

		ismenu = true;
		layout.show(window, "menu");
		change();

		add(window, BorderLayout.CENTER);

		setVisible(true);
	}

	public void setGame(Game g) {
		gamepanel.setGame(g);
	}

	public GamePanel getGamePanel() {
		return gamepanel;
	}

	public void change() {
		// setVisible(false);
		layout.show(window, ismenu ? "game" : "menu");
		ismenu = !ismenu;
		// setVisible(true);
		// repaint();
	}

	public void setGameSession(GameSession gameSession) {
		gamepanel.setGameSession(gameSession);
	}
}
