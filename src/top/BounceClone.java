package top;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import view.GameFrame;

/**
 * A program fõ osztálya.
 */
public class BounceClone {

	/**
	 * A dicsõséglista.
	 */
	public static HighScores highscores;

	/**
	 * A dicsõséglistát beolvasó függvény.
	 * 
	 * Ha nem létezik még a "scores.dat" fájl, akkor üres listát hoz létre.
	 */
	public static void open() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					"scores.dat"));
			highscores = (HighScores) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			highscores = new HighScores();
		} catch (Exception e) {
			System.err.println("Unhandled exception.");
			System.exit(1);
		}
	}

	/**
	 * A dicsõséglistát elmentõ függvény.
	 */
	public static void close() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("scores.dat"));
			oos.writeObject(BounceClone.highscores);
			oos.close();
		} catch (Exception ex) {
			System.err.println("Unhandled exception.");
			System.exit(1);
		}
	}

	/**
	 * A program belépési pontja.
	 */
	public static void main(String[] args) {
		open();
		GameFrame gf = new GameFrame();
		gf.setVisible(true);
	}

}
