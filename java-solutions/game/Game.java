package game;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public class Game {
    private final boolean log;
    private final Player[] players;

    public Game(final boolean log, final Player[] players) {
        this.log = log;
        this.players = players;
    }

    public int play(Board board) {
        while (true) {
            for (int i = 0; i < players.length; i++) {
                final int result = move(board, players[i], i);
                if (result != -2) {
                    return result;
                }
            }

        }
    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return (no + 1) % players.length;
        } else if (result == Result.DRAW) {
            log("Draw");
            return -1;
        } else {
            return -2;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
