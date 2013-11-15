package view;

import game.Game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import control.FrameGenerator;
import top.Common;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private GamePanel gamepanel;

	public GameFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Common.width + 6, Common.height + 55);
		setResizable(false);
		setTitle("BounceClone");

		gamepanel = new GamePanel();
		add(gamepanel, BorderLayout.CENTER);

		setVisible(true);
	}

	public void setGame(Game g) {
		gamepanel.setGame(g);
	}
	
	public void setFrameGenerator(FrameGenerator fg) {
		gamepanel.setFrameGenerator(fg);
	}

	public GamePanel getGamePanel() {
		return gamepanel;
	}

}
