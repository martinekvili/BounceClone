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
 * Az ablak amiben az alkalmaz�s fut.
 */
public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * A j�t�k megjelen�t�je.
	 */
	private GamePanel gamepanel;

	/**
	 * A men�.
	 */
	private MenuPanel menupanel;

	/**
	 * A dics�s�glista megjelen�t�je.
	 */
	private ScorePanel scorepanel;

	/**
	 * A j�t�k vez�rl�je.
	 */
	private GameSession gamesession;

	private CardLayout layout;
	private JPanel window;

	/**
	 * Konstruktor.
	 * 
	 * �sszerakja az ablakot, be�ll�tja a megfelel� listenereket, �s kijelz�sre
	 * �ll�tja a men�t.
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
	 * Az aktu�lis j�t�kmenet be�ll�t�s�ra szolg�l� f�ggv�ny.
	 * 
	 * @param game
	 *            - az aktu�lis j�t�k
	 */
	public void setGame(Game game) {
		gamepanel.setGame(game);
	}

	/**
	 * Az aktu�lis j�t�kmenedzser be�ll�t�s�ra szolg�l� f�ggv�ny.
	 * 
	 * @param gameSession
	 *            - az aktu�lis j�t�kmenedzser
	 */
	public void setGameSession(GameSession gameSession) {
		gamesession = gameSession;
	}

	/**
	 * Az aktu�lis j�t�kmenedzser elk�r�s�re szolg�l� f�ggv�ny.
	 * 
	 * @return az aktu�lis j�t�kmenedzser
	 */
	public GameSession getGameSession() {
		return gamesession;
	}

	/**
	 * Megadja, hogy van-e most �ppen fut� j�t�kmenedzser.
	 * 
	 * @return igaz, ha van j�t�kmenedzser
	 */
	public boolean hasGameSession() {
		return (gamesession != null);
	}

	/**
	 * A j�t�kmegjelen�t� elk�r�s�re szolg�l� f�ggv�ny.
	 * 
	 * @return a j�t�kmegjelen�t�
	 */
	public GamePanel getGamePanel() {
		return gamepanel;
	}

	/**
	 * Az ablakban kijelzend� dolgok v�ltogat�s�ra szolg�l� f�ggv�ny.
	 * 
	 * @param s
	 *            - melyik ablakkomponenst akarjuk l�tni
	 */
	public void show(String s) {
		if (s.equals("menu"))
			menupanel.setContinue(hasGameSession());

		layout.show(window, s);
	}

}
