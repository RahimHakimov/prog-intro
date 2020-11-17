package game;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public class Cell {
    private char symbol;

    public Cell() {
        new Cell('E');
    }

    public Cell(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cell) {
            return symbol == ((Cell) obj).symbol;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return symbol;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }
}
