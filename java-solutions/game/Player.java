package game;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public interface Player {
    Move move(Position position, Cell cell);
}
