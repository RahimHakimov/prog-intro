package game;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public interface Position {
    boolean isValid(Move move);

    Cell getCell(int r, int c);

    int getN();

    int getM();

    int getK();
}
