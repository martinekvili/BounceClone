package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import top.BounceClone;
import control.ButtonListener;

/**
 * A dicsõséglistát tartalmazó JPanel.
 */
public class ScorePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor.
	 * 
	 * Létrehozza a megjelenítõ JTable-t, összeállítja a kinézetet, és beállítja
	 * a gombhoz a listenert.
	 * 
	 * @param listener
	 *            - a gombokat figyelõ listener
	 */
	public ScorePanel(ButtonListener listener) {
		setLayout(new BorderLayout());

		JTable table = new JTable(BounceClone.highscores);
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(320);
		table.getColumnModel().getColumn(2).setPreferredWidth(62);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(450, 390));

		JPanel top = new JPanel();
		top.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.CENTER;

		top.add(scroll, gc);

		add(top, BorderLayout.CENTER);

		JPanel bottom = new JPanel();

		JButton back = new JButton("Back");
		back.setActionCommand("back");
		back.addActionListener(listener);
		bottom.add(back);

		add(bottom, BorderLayout.SOUTH);
	}

}
