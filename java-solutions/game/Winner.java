package game;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public class Winner implements Player {
    @Override
    public Move move(final Position position, final Cell cell) {
        return new Move(0, 0, Cell.E);
    }
}
