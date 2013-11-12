package top;

import game.Game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import view.BoardView;
import control.FrameGenerator;

public class Main {

	public static void main(String[] args) {
		Game g = new Game(2);

		JFrame jf = new JFrame();
		BoardView bv = new BoardView(g);
		jf.add(bv, BorderLayout.CENTER);
		jf.setSize(Common.width + 50, Common.height + 50);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		FrameGenerator fg = new FrameGenerator(g, bv);
		fg.start();
	}

}
