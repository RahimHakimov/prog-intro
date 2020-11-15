package game;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public class MNKBoard implements Board, Position {
    int m, n, k;
    int empty;
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;

    public MNKBoard(int m, int n, int k) {
        this.cells = new Cell[m][n];
        this.m = m;
        this.k = k;
        this.n = n;
        empty = n * m;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        empty--;
        int inColumn = 0;
        for (int i = move.getRow(); i < m; ++i) {
            if (cells[i][move.getColumn()] == move.getValue()) {
                inColumn++;
            }else {
                break;
            } if (inColumn >= k) break;
        }
        for (int i = move.getRow() - 1; i > -1; --i) {
            if (cells[i][move.getColumn()] == move.getValue()) {
                inColumn++;
            } else {
                break;
            }
            if (inColumn >= k) break;
        }
        if (inColumn == k) {
            return Result.WIN;
        }
        int inRow = 0;
        for (int i = move.getColumn(); i < n; ++i) {
            if (cells[move.getRow()][i] == move.getValue()) {
                inRow++;
            } else {
                break;
            }
            if (inRow >= k) break;
        }
        for (int i = move.getColumn() - 1; i > -1; --i) {
            if (cells[move.getRow()][i] == move.getValue()) {
                inRow++;
            } else {
                break;
            }
            if (inRow >= k) break;
        }
        if (inRow == k) {
            return Result.WIN;
        }
        int inDiag1 = 0;
        for (int i = 0; i<=Math.min(move.getColumn(), move.getRow()); ++i) {
            if (cells[move.getRow()-i][move.getColumn()-i] == move.getValue()) {
                inDiag1++;
            } else {
                break;
            }
            if (inDiag1 >= k) break;
        }
        for (int i = 1; i<=Math.min(n-move.getColumn()-1, m-move.getRow()-1); ++i) {
            if (cells[move.getRow()+i][move.getColumn()+i] == move.getValue()) {
                inDiag1++;
            } else {
                break;
            }
            if (inDiag1 >= k) break;
        }
        if (inDiag1 == k) {
            return Result.WIN;
        }
        int inDiag2 = 0;
        for (int i = 0; i<=Math.min(move.getColumn(), m-move.getRow()-1); ++i) {
            if (cells[move.getRow()+i][move.getColumn()-i] == move.getValue()) {
                inDiag2++;
            } else {
                break;
            }
            if (inDiag2 >= k) break;
        }
        for (int i = 1; i<=Math.min(n-move.getColumn()-1, move.getRow()); ++i) {
            if (cells[move.getRow()-i][move.getColumn()+i] == move.getValue()) {
                inDiag2++;
            } else {
                break;
            }
            if (inDiag2 >= k) break;
        }
        if (inDiag2 == k) {
            return Result.WIN;
        }
        if (empty == 0) {
            return Result.DRAW;
        }
        if (turn == Cell.X) turn = Cell.O;
        else if (turn == Cell.O) turn = Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int c = 0; c<n; ++c) {
            sb.append(c);
        }
        for (int r = 0; r < m; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < n; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
