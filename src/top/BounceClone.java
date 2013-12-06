package top;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import view.GameFrame;

/**
 * A program f� oszt�lya.
 */
public class BounceClone {

	/**
	 * A dics�s�glista.
	 */
	public static HighScores highscores;

	/**
	 * A dics�s�glist�t beolvas� f�ggv�ny.
	 * 
	 * Ha nem l�tezik m�g a "scores.dat" f�jl, akkor �res list�t hoz l�tre.
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
	 * A dics�s�glist�t elment� f�ggv�ny.
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
	 * A program bel�p�si pontja.
	 */
	public static void main(String[] args) {
		open();
		GameFrame gf = new GameFrame();
		gf.setVisible(true);
	}

}
