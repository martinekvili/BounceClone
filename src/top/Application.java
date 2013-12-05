package top;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

import view.GameFrame;

public class Application {

	public static HighScores highscores;

	public static void main(String[] args) {
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

		GameFrame gf = new GameFrame();
		gf.setVisible(true);
	}

}
