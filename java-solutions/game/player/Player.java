package game.player;

import game.Cell;
import game.Move;
import game.Position;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public interface Player {
    Move move(Position position, Cell cell);
}
