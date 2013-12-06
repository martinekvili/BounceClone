package control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import top.BounceClone;

/**
 * A kil�p�skor v�gzend� ment�st megval�s�t� oszt�ly.
 */
public class WindowListener extends WindowAdapter {

	/**
	 * Akkor fut le, amikor kil�p�nk a program.
	 */
	public void windowClosing(WindowEvent e) {
		BounceClone.close();
	}

}
