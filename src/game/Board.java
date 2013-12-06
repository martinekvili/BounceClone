package game;

import top.Common;

/**
 * A p�ly�t reprezent�l� oszt�ly.
 * 
 * Val�j�ban egy t�bl�zat, melynek minden cell�j�nak k�l�nb�z� �llapota lehet:
 * �res, fal, �p�l� fal.
 */
public class Board {

	/**
	 * A p�ly�n l�v� fel�p�lt falak sz�ma. A sz�zal�ksz�m�t�shoz sz�ks�ges.
	 */
	private int wallnum;

	/**
	 * A j�t�k amihez a p�lya tartozik.
	 */
	private Game parent;

	/**
	 * A p�lya cell�inak �llapotai.
	 */
	public enum BoardState {

		/**
		 * �res.
		 */
		EMPTY,

		/**
		 * Fel�p�lt fal.
		 */
		WALL,

		/**
		 * �p�l� fal.
		 */
		UNDER_CONSTRUCTION,

		/**
		 * �p�l� fal, aminek nekiment egy labda, �gy nem �p�l tov�bb, hanem
		 * let�rl�dik.
		 */
		BROKEN_WALL,

		/**
		 * Olyan cella, ahol tart�zkodik labda: felt�lt�sn�l l�nyeges.
		 */
		BALL
	};

	/**
	 * A p�ly�t le�r� t�bl�zat.
	 */
	private BoardState[][] board;

	/**
	 * Konstruktor, ami l�trehozza az �res p�ly�t.
	 * 
	 * @param game
	 *            - a j�t�k amihez a p�lya tartozik
	 */
	public Board(Game game) {
		wallnum = 0;

		parent = game;

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

	/**
	 * F�ggv�ny, amellyel lek�rdezhetj�k a p�lya egy adott cell�j�nak �llapot�t.
	 * 
	 * @param pos
	 *            - a k�rd�ses cella koordin�t�i
	 * @return a k�rd�ses cella �llapota
	 */
	public BoardState getState(BoardPos pos) {
		return board[pos.ypos][pos.xpos];
	}

	/**
	 * F�ggv�ny, amellyel be�ll�thatjuk a p�lya egy adott cell�j�nak �llapot�t.
	 * 
	 * Ha fel�p�lt falat �ll�tunk be, akkor n�veli a fel�p�lt falak sz�m�t. �gy
	 * oldjuk meg hogy az ebben a v�ltoz�ban t�rolt �rt�k mindig konzisztens
	 * maradjon.
	 * 
	 * @param pos
	 *            - a k�rd�ses cella koordin�t�i
	 * @param stat
	 *            - a be�ll�tand� �llapot
	 */
	public void setState(BoardPos pos, BoardState stat) {
		board[pos.ypos][pos.xpos] = stat;
		if (stat == BoardState.WALL)
			wallnum++;
	}

	/**
	 * F�ggv�ny, ami felt�lti az adott poz�ci�b�l el�rhet� ter�leteket.
	 * 
	 * El�rhet� egy ter�let, ha nem v�lasztja el fal a kezd�pontt�l. Csak akkor
	 * t�ltj�k fel a ter�letet, ha egyetlen cell�j�ban sincs labda.
	 * 
	 * Val�j�ban ez a f�ggv�ny csak egy bor�t� a t�nyleges rekurz�v f�ggv�nyek
	 * felett, de k�v�lr�l csak ez �rhet� el.
	 * 
	 * @param pos
	 *            - a kezd�poz�ci� koordin�t�i
	 */
	public void fillFromPos(BoardPos pos) {
		if (test(pos))
			fill(pos);
	}

	/**
	 * A val�di rekurz�van felt�lt� f�ggv�ny.
	 * 
	 * @param pos
	 *            - az aktu�lis poz�ci� koordin�t�i
	 */
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

	/**
	 * A f�ggv�ny ami ellen�rzi, hogy az adott kezd�poz�ci�b�l v�gezhet�nk-e
	 * felt�lt�st.
	 * 
	 * L�trehoz egy pr�ba-p�ly�t, amit elkezd felt�lteni, �s ha k�zben
	 * tal�lkozik olyan cell�val, amiben labda van, akkor jelez, hogy innen nem
	 * v�gezhet�nk felt�lt�st, am�gy pedig igen.
	 * 
	 * @param pos
	 *            - a kezd�poz�ci� koordin�t�i
	 * @return igaz, ha innen v�gezhet�nk felt�lt�st
	 */
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

	/**
	 * Rekurz�v f�ggv�ny, ami a t�nyleges ellen�rz�st v�gzi.
	 * 
	 * @param pos
	 *            - az aktu�lis poz�ci� koordin�t�i
	 * @param sandbox
	 *            - a pr�ba-p�lya amin �ppen dolgozunk
	 * @return igaz, ha v�gezhet�nk felt�lt�st
	 */
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

	/**
	 * F�ggv�ny, amivel lek�rdezhetj�k, hogy a p�lya h�ny sz�zal�ka van
	 * felt�ltve fel�p�lt fallal.
	 * 
	 * @return a betel�tett ter�let sz�zal�kban
	 */
	public int getPercent() {
		int all = (Common.boardheight - 2) * (Common.boardwidth - 2);

		double percent = (double) wallnum / all;

		return (int) (percent * 100);
	}
}
