package game;

import top.Common;

public class Board {

	public enum State {
		EMPTY, WALL, UNDER_CONSTRUCTION
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
		
		board[10][15] = State.WALL;
		board[9][15] = State.WALL;
		board[11][15] = State.WALL;
		board[12][15] = State.WALL;
		board[8][15] = State.WALL;
		
		board[10][20] = State.WALL;
		board[9][20] = State.WALL;
		board[11][20] = State.WALL;
		
		board[12][10] = State.WALL;
		board[8][10] = State.WALL;
	}

	public State getState(BoardPos pos) {
		return board[pos.ypos][pos.xpos];
	}

	public void setState(BoardPos pos, State stat) {
		board[pos.ypos][pos.xpos] = stat;
	}

}
