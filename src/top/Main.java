package top;

import view.GameFrame;

public class Main {

	public static void main(String[] args) {
		GameFrame gf = new GameFrame();
		GameSession gs = new GameSession(gf);
		gs.game();
		// gf.setFocusable(false);
		// NameInputFrame nif = new NameInputFrame();
	}

}
