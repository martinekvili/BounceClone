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
 * A kattint�sokat feldolgoz� oszt�ly.
 */
public class WallBuilder extends MouseAdapter {

	/**
	 * A kurzor aktu�lis �ll�sa.
	 */
	private Orientation dir;

	/**
	 * A f�gg�legesen �ll� kurzor.
	 */
	private Cursor vertical;

	/**
	 * A v�zszintesen �ll� kurzor.
	 */
	private Cursor horizontal;

	/**
	 * A j�t�k, amiben a kattint�sokkal a falakat �p�tj�k.
	 */
	private Game game;

	/**
	 * Konstruktor.
	 * 
	 * @param game
	 *            - a j�t�k amiben a falakat �p�tj�k
	 */
	public WallBuilder(Game game) {
		dir = Orientation.VERTICAL;

		vertical = new Cursor(Cursor.N_RESIZE_CURSOR);
		horizontal = new Cursor(Cursor.E_RESIZE_CURSOR);

		this.game = game;
	}

	/**
	 * A f�ggv�ny, ami kattint�skor h�v�dik.
	 * 
	 * Bal eg�rgombbal a falat kezdj�k el �p�teni, m�g jobb eg�rgombbal az
	 * �p�t�s ir�ny�t tudjuk v�ltoztatni.
	 * 
	 * Egy kattint�s hat�s�ra k�t �p�l� fal indul el, egy pozit�v, egy negat�v
	 * ir�nyba.
	 * 
	 * Csak akkor kezd�nk el egy adott helyen �p�tkezni, ha az m�g p�ly�n bel�l
	 * esik, �s m�g nincs ott fal.
	 * 
	 * Csak akkor szabad �p�tkezni, ha a j�t�kban �ppen nincsen m�sik akt�v
	 * �p�tkez�s folyamatban.
	 */
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {

		/* Balklikk �rkezett. */
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

		/* Jobklikk �rkezett. */
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
	 * Ezzel a f�ggv�nnyel tudjuk m�sik j�t�kra r��ll�tani az oszt�lyt.
	 * 
	 * @param game
	 *            - a j�t�k amiben a falakat �p�tj�k
	 */
	public void setGame(Game game) {
		this.game = game;
	}

}
