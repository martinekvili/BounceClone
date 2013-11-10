package game;

import top.Common;

public class Board {

	public enum State {
		EMPTY, WALL, UNDER_CONSTRUCTION
	};

	private State[][] board;

	public Board() {
		board = new State[Common.boardwidth + 2][Common.boardwidth + 2];

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
		return board[pos.xpos + 1][pos.ypos + 1];
	}

	public void setState(BoardPos pos, State stat) {
		board[pos.xpos + 1][pos.ypos + 1] = stat;
	}

}
