package view;

import game.Game;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import top.Common;
import top.GameSession;
import control.ButtonListener;
import control.WindowListener;

/**
 * Az ablak amiben az alkalmazás fut.
 */
public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * A játék megjelenítõje.
	 */
	private GamePanel gamepanel;

	/**
	 * A menü.
	 */
	private MenuPanel menupanel;

	/**
	 * A dicsõséglista megjelenítõje.
	 */
	private ScorePanel scorepanel;

	/**
	 * A játék vezérlõje.
	 */
	private GameSession gamesession;

	private CardLayout layout;
	private JPanel window;

	/**
	 * Konstruktor.
	 * 
	 * Összerakja az ablakot, beállítja a megfelelõ listenereket, és kijelzésre
	 * állítja a menüt.
	 */
	public GameFrame() {
		addWindowListener(new WindowListener());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Common.width + 6, Common.height + 55);
		setResizable(false);
		setTitle("BounceClone");
		setLocation(100, 100);

		ButtonListener listener = new ButtonListener(this);

		window = new JPanel();

		layout = new CardLayout();
		window.setLayout(layout);

		gamepanel = new GamePanel(listener);
		menupanel = new MenuPanel(listener);
		scorepanel = new ScorePanel(listener);

		window.add(menupanel, "menu");
		window.add(gamepanel, "game");
		window.add(scorepanel, "scores");

		add(window, BorderLayout.CENTER);

		gamesession = null;

		show("menu");
	}

	/**
	 * Az aktuális játékmenet beállítására szolgáló függvény.
	 * 
	 * @param game
	 *            - az aktuális játék
	 */
	public void setGame(Game game) {
		gamepanel.setGame(game);
	}

	/**
	 * Az aktuális játékmenedzser beállítására szolgáló függvény.
	 * 
	 * @param gameSession
	 *            - az aktuális játékmenedzser
	 */
	public void setGameSession(GameSession gameSession) {
		gamesession = gameSession;
	}

	/**
	 * Az aktuális játékmenedzser elkérésére szolgáló függvény.
	 * 
	 * @return az aktuális játékmenedzser
	 */
	public GameSession getGameSession() {
		return gamesession;
	}

	/**
	 * Megadja, hogy van-e most éppen futó játékmenedzser.
	 * 
	 * @return igaz, ha van játékmenedzser
	 */
	public boolean hasGameSession() {
		return (gamesession != null);
	}

	/**
	 * A játékmegjelenító elkérésére szolgáló függvény.
	 * 
	 * @return a játékmegjelenítõ
	 */
	public GamePanel getGamePanel() {
		return gamepanel;
	}

	/**
	 * Az ablakban kijelzendõ dolgok váltogatására szolgáló függvény.
	 * 
	 * @param s
	 *            - melyik ablakkomponenst akarjuk látni
	 */
	public void show(String s) {
		if (s.equals("menu"))
			menupanel.setContinue(hasGameSession());

		layout.show(window, s);
	}

}
