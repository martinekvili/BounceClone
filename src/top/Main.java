package top;

import game.Game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import view.GamePanel;
import control.FrameGenerator;

public class Main {

	public static void main(String[] args) {
		Game g = new Game(2);

		JFrame jf = new JFrame();
		GamePanel gp = new GamePanel(g);
		jf.add(gp, BorderLayout.CENTER);
		jf.setSize(Common.width + 6, Common.height + 55);
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		FrameGenerator fg = new FrameGenerator(g, gp);
		fg.start();
	}

}
