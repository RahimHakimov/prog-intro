package game;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public interface Board {
    Position getPosition();

    Cell getCell();

    Result makeMove(Move move);
}
