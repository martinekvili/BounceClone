package game;

import top.Common;

public class Board {

	private int wallnum;

	private Game parent;

	public enum BoardState {
		EMPTY, WALL, UNDER_CONSTRUCTION, BROKEN_WALL, BALL
	};

	private BoardState[][] board;

	public Board(Game p) {
		wallnum = 0;

		parent = p;

		board = new BoardState[Common.boardheight][Common.boardwidth];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (i == 0 || i == board.length - 1 || j == 0
						|| j == board[i].length - 1)
					board[i][j] = BoardState.WALL;
				else
					board[i][j] = BoardState.EMPTY;
			}
		}
	}

	public BoardState getState(BoardPos pos) {
		return board[pos.ypos][pos.xpos];
	}

	public void setState(BoardPos pos, BoardState stat) {
		board[pos.ypos][pos.xpos] = stat;
		if (stat == BoardState.WALL)
			wallnum++;
	}

	public void fillFromPos(BoardPos pos) {
		if (test(pos))
			fill(pos);
	}

	private void fill(BoardPos pos) {
		switch (getState(pos)) {
		case EMPTY:
			setState(pos, BoardState.WALL);
			fill(new BoardPos(pos.xpos + 1, pos.ypos));
			fill(new BoardPos(pos.xpos - 1, pos.ypos));
			fill(new BoardPos(pos.xpos, pos.ypos + 1));
			fill(new BoardPos(pos.xpos, pos.ypos - 1));
			return;

		default:
			return;
		}
	}

	private boolean test(BoardPos pos) {
		if (getState(pos) != BoardState.EMPTY)
			return false;

		BoardState[][] sandbox = new BoardState[Common.boardheight][Common.boardwidth];
		for (int i = 0; i < sandbox.length; i++) {
			for (int j = 0; j < sandbox[i].length; j++) {
				sandbox[i][j] = board[i][j];
			}
		}

		for (int i = 0; i < parent.balls; i++) {
			Vector vec = parent.objects.get(i).getVec();
			BoardPos tmp = BoardPos.vecToPos(vec);
			sandbox[tmp.ypos][tmp.xpos] = BoardState.BALL;
		}

		return recursiveTest(pos, sandbox);
	}

	private boolean recursiveTest(BoardPos pos, BoardState[][] sandbox) {
		switch (sandbox[pos.ypos][pos.xpos]) {
		case WALL:
			return true;

		case BALL:
			return false;

		case EMPTY:
			sandbox[pos.ypos][pos.xpos] = BoardState.WALL;
			return (recursiveTest(new BoardPos(pos.xpos, pos.ypos + 1), sandbox)
					&& recursiveTest(new BoardPos(pos.xpos, pos.ypos - 1),
							sandbox)
					&& recursiveTest(new BoardPos(pos.xpos + 1, pos.ypos),
							sandbox) && recursiveTest(new BoardPos(
					pos.xpos - 1, pos.ypos), sandbox));

		default:
			return false;
		}
	}

	public int getPercent() {
		int all = (Common.boardheight - 2) * (Common.boardwidth - 2);

		double percent = (double) wallnum / all;

		return (int) (percent * 100);
	}
}
