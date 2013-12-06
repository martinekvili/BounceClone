package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import top.BounceClone;
import control.ButtonListener;

public class ScorePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ScorePanel(ButtonListener listener) {
		setLayout(new BorderLayout());

		JPanel top = new JPanel();

		JTable table = new JTable(BounceClone.highscores);
		table.setFillsViewportHeight(true);

		JScrollPane scroll = new JScrollPane(table);

		top.add(scroll);

		JPanel bottom = new JPanel();

		JButton back = new JButton("Back");
		back.setActionCommand("back");
		back.addActionListener(listener);
		bottom.add(back);

		add(top, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
	}

}
