package game;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public class MNKBoard implements Board, Position {
    private final Cell emptyCell = new Cell();
    private Map<Cell, Character> SYMBOLS;

    private final Cell[][] cells;
    private final int k;
    private int notEmpty = 0;
    private char turn;

    public MNKBoard(int n, int m, int k) {
        this.cells = new Cell[n][m];
        this.k = k;
        turn = 'X';
        SYMBOLS = new HashMap<>();
        SYMBOLS.put(new Cell('X'), 'X');
        SYMBOLS.put(new Cell('O'), 'O');
        SYMBOLS.put(emptyCell, '.');
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    @Override
    public Position getPosition() {
        return new ProxyPosition(this);
    }

    @Override
    public Cell getCell() {
        return new Cell(turn);
    }

    private int NumberOfEquals(int r, int c, int i, int j) {
        int cnt = 0;
        while (r + i >= 0 && c + j >= 0
                && r + i < cells.length
                && c + j < cells[0].length
                && cells[r + i][c + j].equals(new Cell(turn))
                && cnt < getK()) {
            cnt++;
            r += i;
            c += j;
        }
        return cnt;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        int r = move.getRow(), c = move.getColumn();
        cells[r][c] = move.getValue();

        if (areWin(r, c, 0, 1) || areWin(r, c, 1, 0)
                || areWin(r, c, 1, 1) || areWin(r, c, 1, -1)) {
            return Result.WIN;
        }

        if (++notEmpty == getN() * getM()) {
            return Result.DRAW;
        }
        if (turn == 'X') turn = 'O';
        else turn = 'X';
        return Result.UNKNOWN;
    }

    private boolean areWin(int r, int c, int d1, int d2) {
        return NumberOfEquals(r, c, d1, d2) + NumberOfEquals(r, c, -d1, -d2) + 1 >= k;
    }

    @Override
    public int getN() {
        return cells[0].length;
    }

    @Override
    public int getM() {
        return cells.length;
    }

    @Override
    public int getK() {
        return k;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < cells.length
                && 0 <= move.getColumn() && move.getColumn() < cells[0].length
                && cells[move.getRow()][move.getColumn()].equals(emptyCell)
                && getCell().equals(new Cell(turn));
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        int width = Math.max(
                String.valueOf(cells[0].length).length(),
                String.valueOf(cells.length).length()
        );

        final StringBuilder sb = new StringBuilder(" ".repeat(width + 1));

        for (int c = 0; c < cells[0].length; c++) {
            sb.append(c + 1).append(" ");
        }
        for (int r = 0; r < cells.length; r++) {
            sb.append("\n");
            sb.append(r + 1).append(" ");
            for (int c = 0; c < cells[0].length; c++) {
                sb.append(SYMBOLS.get(cells[r][c])).append(" ");
            }
        }

        return sb.toString();
    }
}
