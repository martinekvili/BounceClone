package control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import top.BounceClone;

/**
 * A kilépéskor végzendõ mentést megvalósító osztály.
 */
public class WindowListener extends WindowAdapter {

	/**
	 * Akkor fut le, amikor kilépünk a program.
	 */
	public void windowClosing(WindowEvent e) {
		BounceClone.close();
	}

}
