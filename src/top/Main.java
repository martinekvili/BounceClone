package top;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import view.BoardView;
import game.Game;

public class Main {

	public static void main(String[] args) {
		Game g = new Game(50);
		
		JFrame jf = new JFrame();
		BoardView bv = new BoardView(g);
		jf.add(bv, BorderLayout.CENTER);
		jf.setSize(Common.width, Common.height);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			g.step();
			bv.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
