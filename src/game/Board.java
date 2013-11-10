package game;

import top.Common;

public class Board {

	public enum State {
		EMPTY, WALL, UNDER_CONSTRUCTION, BROKEN_WALL
	};

	private State[][] board;

	public Board() {
		board = new State[Common.boardheight][Common.boardwidth];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (i == 0 || i == board.length - 1 || j == 0
						|| j == board[i].length - 1)
					board[i][j] = State.WALL;
				else
					board[i][j] = State.EMPTY;
			}
		}
	}

	public State getState(BoardPos pos) {
		return board[pos.ypos][pos.xpos];
	}

	public void setState(BoardPos pos, State stat) {
		board[pos.ypos][pos.xpos] = stat;
	}

}
