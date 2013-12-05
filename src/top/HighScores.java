package top;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class HighScores extends AbstractTableModel implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Score> highscores;

	public HighScores() {
		highscores = new ArrayList<Score>();
	}

	public int getColumnCount() {
		return 3;
	}

	public int getRowCount() {
		return highscores.size();
	}

	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return Integer.toString(row + 1) + ".";
		case 1:
			return highscores.get(row).name;
		default:
			return highscores.get(row).points;
		}
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case 0:
		case 1:
			return String.class;
		default:
			return Integer.class;
		}
	}

	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Place";
		case 1:
			return "Name";
		default:
			return "Points";
		}
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public void add(Score s) {
		highscores.add(s);
		Collections.sort(highscores);
	}

}
