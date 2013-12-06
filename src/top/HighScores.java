package top;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * A dics�s�glist�t tartalmaz� oszt�ly.
 * 
 * Egy JTable modelljek�nt is szolg�l, �s serializ�lhat�.
 */
public class HighScores extends AbstractTableModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * A pontokat tartalmaz� lista.
	 */
	public List<Score> highscores;

	/**
	 * Konstruktor.
	 * 
	 * �res list�t hoz l�tre.
	 */
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

	/**
	 * Ezzel a f�ggv�nnyel tudunk �j pontsz�mot hozz�adni a list�hoz.
	 * 
	 * A f�ggv�ny hozz�adja az �j pontsz�mot, �s r�gt�n �jra is rendezi a
	 * list�t.
	 * 
	 * @param score
	 *            - az �j pontsz�m
	 */
	public void add(Score score) {
		highscores.add(score);
		Collections.sort(highscores);
	}

}
