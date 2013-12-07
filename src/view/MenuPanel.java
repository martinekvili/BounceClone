package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import control.ButtonListener;

/**
 * A menüt tartalmazó JPanel.
 */
public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * A folytatás gomb.
	 */
	private JButton cont;

	/**
	 * Konstruktor.
	 * 
	 * Összerakja a kinézetet, és beállítja a gombokhoz a listenert.
	 * 
	 * @param listener
	 *            - a gombokat figyelõ listener
	 */
	public MenuPanel(ButtonListener listener) {
		setLayout(new GridBagLayout());

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

		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.CENTER;

		add(panel, gc);
	}

	/**
	 * Beálítja, hogy elérhetõ legyen-e a folytatás gomb.
	 * 
	 * A folytatás gomb csak akkor elérhetõ, ha van éppen félbehagyott játék.
	 * 
	 * @param enabled
	 *            - az érték amire be kell állítani
	 */
	public void setContinue(boolean enabled) {
		cont.setEnabled(enabled);
	}

}
