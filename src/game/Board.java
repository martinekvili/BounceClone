package game;

import top.Common;

/**
 * A pályát reprezentáló osztály.
 * 
 * Valójában egy táblázat, melynek minden cellájának különbözõ állapota lehet:
 * üres, fal, épülõ fal.
 */
public class Board {

	/**
	 * A pályán lévõ felépült falak száma. A százalékszámításhoz szükséges.
	 */
	private int wallnum;

	/**
	 * A játék amihez a pálya tartozik.
	 */
	private Game parent;

	/**
	 * A pálya celláinak állapotai.
	 */
	public enum BoardState {

		/**
		 * Üres.
		 */
		EMPTY,

		/**
		 * Felépült fal.
		 */
		WALL,

		/**
		 * Épülõ fal.
		 */
		UNDER_CONSTRUCTION,

		/**
		 * Épülõ fal, aminek nekiment egy labda, így nem épül tovább, hanem
		 * letörlõdik.
		 */
		BROKEN_WALL,

		/**
		 * Olyan cella, ahol tartózkodik labda: feltöltésnél lényeges.
		 */
		BALL
	};

	/**
	 * A pályát leíró táblázat.
	 */
	private BoardState[][] board;

	/**
	 * Konstruktor, ami létrehozza az üres pályát.
	 * 
	 * @param game
	 *            - a játék amihez a pálya tartozik
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
	 * Függvény, amellyel lekérdezhetjük a pálya egy adott cellájának állapotát.
	 * 
	 * @param pos
	 *            - a kérdéses cella koordinátái
	 * @return a kérdéses cella állapota
	 */
	public BoardState getState(BoardPos pos) {
		return board[pos.ypos][pos.xpos];
	}

	/**
	 * Függvény, amellyel beállíthatjuk a pálya egy adott cellájának állapotát.
	 * 
	 * Ha felépült falat állítunk be, akkor növeli a felépült falak számít. Így
	 * oldjuk meg hogy az ebben a változóban tárolt érték mindig konzisztens
	 * maradjon.
	 * 
	 * @param pos
	 *            - a kérdéses cella koordinátái
	 * @param stat
	 *            - a beállítandó állapot
	 */
	public void setState(BoardPos pos, BoardState stat) {
		board[pos.ypos][pos.xpos] = stat;
		if (stat == BoardState.WALL)
			wallnum++;
	}

	/**
	 * Függvény, ami feltölti az adott pozícióból elérhetõ területeket.
	 * 
	 * Elérhetõ egy terület, ha nem választja el fal a kezdõponttól. Csak akkor
	 * töltjük fel a területet, ha egyetlen cellájában sincs labda.
	 * 
	 * Valójában ez a függvény csak egy borító a tényleges rekurzív függvények
	 * felett, de kívülrõl csak ez érhetõ el.
	 * 
	 * @param pos
	 *            - a kezdõpozíció koordinátái
	 */
	public void fillFromPos(BoardPos pos) {
		if (test(pos))
			fill(pos);
	}

	/**
	 * A valódi rekurzívan feltöltõ függvény.
	 * 
	 * @param pos
	 *            - az aktuális pozíció koordinátái
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
	 * A függvény ami ellenõrzi, hogy az adott kezdõpozícióból végezhetünk-e
	 * feltöltést.
	 * 
	 * Létrehoz egy próba-pályát, amit elkezd feltölteni, és ha közben
	 * találkozik olyan cellával, amiben labda van, akkor jelez, hogy innen nem
	 * végezhetünk feltöltést, amúgy pedig igen.
	 * 
	 * @param pos
	 *            - a kezdõpozíció koordinátái
	 * @return igaz, ha innen végezhetünk feltöltést
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
	 * Rekurzív függvény, ami a tényleges ellenõrzést végzi.
	 * 
	 * @param pos
	 *            - az aktuális pozíció koordinátái
	 * @param sandbox
	 *            - a próba-pálya amin éppen dolgozunk
	 * @return igaz, ha végezhetünk feltöltést
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
	 * Függvény, amivel lekérdezhetjük, hogy a pálya hány százaléka van
	 * feltöltve felépült fallal.
	 * 
	 * @return a betelített terület százalékban
	 */
	public int getPercent() {
		int all = (Common.boardheight - 2) * (Common.boardwidth - 2);

		double percent = (double) wallnum / all;

		return (int) (percent * 100);
	}
}
