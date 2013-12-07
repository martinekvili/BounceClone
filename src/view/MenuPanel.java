package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import control.ButtonListener;

/**
 * A men�t tartalmaz� JPanel.
 */
public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * A folytat�s gomb.
	 */
	private JButton cont;

	/**
	 * Konstruktor.
	 * 
	 * �sszerakja a kin�zetet, �s be�ll�tja a gombokhoz a listenert.
	 * 
	 * @param listener
	 *            - a gombokat figyel� listener
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
	 * Be�l�tja, hogy el�rhet� legyen-e a folytat�s gomb.
	 * 
	 * A folytat�s gomb csak akkor el�rhet�, ha van �ppen f�lbehagyott j�t�k.
	 * 
	 * @param enabled
	 *            - az �rt�k amire be kell �ll�tani
	 */
	public void setContinue(boolean enabled) {
		cont.setEnabled(enabled);
	}

}
