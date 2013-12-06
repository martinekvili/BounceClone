package control;

import game.Board.BoardState;
import game.BoardPos;
import game.Game;
import game.HalfDoneWall;
import game.HalfDoneWall.Direction;
import game.HalfDoneWall.Orientation;
import game.Vector;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import top.Common;

/**
 * A kattintásokat feldolgozó osztály.
 */
public class WallBuilder extends MouseAdapter {

	/**
	 * A kurzor aktuális állása.
	 */
	private Orientation dir;

	/**
	 * A függõlegesen álló kurzor.
	 */
	private Cursor vertical;

	/**
	 * A vízszintesen álló kurzor.
	 */
	private Cursor horizontal;

	/**
	 * A játék, amiben a kattintásokkal a falakat építjük.
	 */
	private Game game;

	/**
	 * Konstruktor.
	 * 
	 * @param game
	 *            - a játék amiben a falakat építjük
	 */
	public WallBuilder(Game game) {
		dir = Orientation.VERTICAL;

		vertical = new Cursor(Cursor.N_RESIZE_CURSOR);
		horizontal = new Cursor(Cursor.E_RESIZE_CURSOR);

		this.game = game;
	}

	/**
	 * A függvény, ami kattintáskor hívódik.
	 * 
	 * Bal egérgombbal a falat kezdjük el építeni, míg jobb egérgombbal az
	 * építés irányát tudjuk változtatni.
	 * 
	 * Egy kattintás hatására két épülõ fal indul el, egy pozitív, egy negatív
	 * irányba.
	 * 
	 * Csak akkor kezdünk el egy adott helyen építkezni, ha az még pályán belül
	 * esik, és még nincs ott fal.
	 * 
	 * Csak akkor szabad építkezni, ha a játékban éppen nincsen másik aktív
	 * építkezés folyamatban.
	 */
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {

		/* Balklikk érkezett. */
		case 1:
			if (game.freeToBulid()) {
				BoardPos pos1 = BoardPos
						.vecToPos(new Vector(e.getX(), e.getY()));
				BoardPos pos2 = new BoardPos(pos1.xpos, pos1.ypos);
				if (dir == Orientation.HORIZONTAL) {
					pos1.xpos = (int) Math.round((double) e.getX()
							/ Common.squaresize);
					pos2.xpos = pos1.xpos - 1;
				} else {
					pos1.ypos = (int) Math.round((double) e.getY()
							/ Common.squaresize);
					pos2.ypos = pos1.ypos - 1;
				}

				if (pos1.xpos < Common.boardwidth
						&& pos1.ypos < Common.boardheight
						&& game.board.getState(pos1) != BoardState.WALL)
					game.addObject(new HalfDoneWall(game, pos1, dir,
							Direction.POSITIVE));

				if (pos2.xpos >= 0 && pos2.ypos >= 0
						&& game.board.getState(pos2) != BoardState.WALL)
					game.addObject(new HalfDoneWall(game, pos2, dir,
							Direction.NEGATIVE));
			}
			break;

		/* Jobklikk érkezett. */
		case 3:
			if (dir == Orientation.VERTICAL) {
				dir = Orientation.HORIZONTAL;
				e.getComponent().setCursor(horizontal);
			} else {
				dir = Orientation.VERTICAL;
				e.getComponent().setCursor(vertical);
			}
			break;

		default:
			break;
		}
	}

	/**
	 * Ezzel a függvénnyel tudjuk másik játékra ráállítani az osztályt.
	 * 
	 * @param game
	 *            - a játék amiben a falakat építjük
	 */
	public void setGame(Game game) {
		this.game = game;
	}

}
