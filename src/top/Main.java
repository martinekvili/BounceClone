package top;

import game.Game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import view.BoardView;

public class Main {

	public static void main(String[] args) {
		Game g = new Game(2);

		JFrame jf = new JFrame();
		BoardView bv = new BoardView(g);
		jf.add(bv, BorderLayout.CENTER);
		jf.setSize(Common.width + 50, Common.height + 50);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		long startime = System.currentTimeMillis();
		long framecounter = 0;

		while (true) {
			framecounter++;
			g.step();
			bv.repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println((double) framecounter
			//		/ (System.currentTimeMillis() - startime) * 1000);
		}
	}

}
