package control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import top.Application;

public class WindowListener extends WindowAdapter {

	public void windowClosing(WindowEvent e) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("scores.dat"));
			oos.writeObject(Application.highscores);
			oos.close();
		} catch (Exception ex) {
			System.err.println("Unhandled exception.");
			System.exit(1);
		}
	}

}
