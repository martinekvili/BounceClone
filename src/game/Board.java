package game;

import top.Common;

public class Board {

	private int wallnum;

	private Game parent;

	public enum State {
		EMPTY, WALL, UNDER_CONSTRUCTION, BROKEN_WALL, BALL
	};

	private State[][] board;

	public Board(Game p) {
		wallnum = 0;

		parent = p;

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
		if (stat == State.WALL)
			wallnum++;
	}

	public void fillFromPos(BoardPos pos) {
		if (test(pos))
			fill(pos);
	}

	private void fill(BoardPos pos) {
		switch (getState(pos)) {
		case EMPTY:
			setState(pos, State.WALL);
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
		if (getState(pos) != State.EMPTY)
			return false;

		State[][] sandbox = new State[Common.boardheight][Common.boardwidth];
		for (int i = 0; i < sandbox.length; i++) {
			for (int j = 0; j < sandbox[i].length; j++) {
				sandbox[i][j] = board[i][j];
			}
		}

		for (int i = 0; i < parent.balls; i++) {
			Vector vec = parent.objects.get(i).getVec();
			BoardPos tmp = BoardPos.vecToPos(vec);
			sandbox[tmp.ypos][tmp.xpos] = State.BALL;
		}

		return recursiveTest(pos, sandbox);
	}

	private boolean recursiveTest(BoardPos pos, State[][] sandbox) {
		switch (sandbox[pos.ypos][pos.xpos]) {
		case WALL:
			return true;

		case BALL:
			return false;

		case EMPTY:
			sandbox[pos.ypos][pos.xpos] = State.WALL;
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
