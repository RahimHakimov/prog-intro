package game;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public class Tournament {
    private final boolean log;
    private final Player[] players;
    private final int numberOfPlayers;
    private int[] results;

    public Tournament(final boolean log, final int numberOfPlayers, final Player[] players) {
        this.log = log;
        this.players = players;
        this.numberOfPlayers = numberOfPlayers;
    }

    public int[] whoIsTheBestPlayer(Board board, int c, int numberOfGames) {
        results = new int[numberOfPlayers];
        int[][] TableOfResults = new int[numberOfPlayers][numberOfPlayers];
        for (int t = 0; t < c; ++t) {
            for (int i = 0; i < numberOfPlayers; ++i) {
                for (int j = i + 1; j < numberOfPlayers; ++j) {
                    Match match = new Match(log, players[i], players[j], numberOfGames);
                    int result = match.play(board);
                    if (result >= 0) {
                        if (result == 0) {
                            TableOfResults[i][j] = 3;
                            TableOfResults[j][i] = 0;
                            results[i] += 3;
                        } else {
                            TableOfResults[i][j] = 0;
                            TableOfResults[j][i] = 3;
                            results[j] += 3;
                        }
                    } else {
                        TableOfResults[i][j] = 1;
                        TableOfResults[j][i] = 1;
                        results[i] += 1;
                        results[j] += 1;
                    }
                }
            }
            if (log) {
                System.out.print(" ");
                for (int i = 0; i < numberOfPlayers; ++i) {
                    System.out.print(" " + (i + 1));
                }
                System.out.println();
                for (int i = 0; i < numberOfPlayers; ++i) {
                    System.out.print((i + 1) + " ");
                    for (int j = 0; j < numberOfPlayers; ++j) {
                        if (i != j) System.out.print(TableOfResults[i][j] + " ");
                        else System.out.print("# ");
                    }
                    System.out.println();
                }
            }
        }
        return results;
    }

}
