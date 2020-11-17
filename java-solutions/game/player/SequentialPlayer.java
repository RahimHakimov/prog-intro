package game.player;

import game.Board;
import game.Cell;
import game.Move;
import game.Position;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public class SequentialPlayer implements Player {
    @Override
    public Move move(final Position position, final Cell cell) {
        /*Board board = (Board) position;
        board.makeMove(0, 0, cell);*/
        int m = position.getM();
        int n = position.getN();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
