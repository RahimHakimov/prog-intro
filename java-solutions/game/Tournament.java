package game;

import game.player.Player;

import java.util.Random;

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

    public int[] whoIsTheBestPlayer(int m, int n, int k, int c) {
        results = new int[numberOfPlayers];
        int[][] TableOfResults = new int[numberOfPlayers][numberOfPlayers];
        for (int t = 0; t < c; ++t) {
            for (int i = 0; i < numberOfPlayers; ++i) {
                for (int j = i + 1; j < numberOfPlayers; ++j) {
                    boolean whoIsFirst = new Random().nextBoolean();
                    Game game = new Game(log, new Player[]{players[j], players[i]});
                    if (whoIsFirst) {
                        game = new Game(log, new Player[]{players[i], players[j]});
                    }
                    int result = game.play(new MNKBoard(m, n, k));
                    if (result >= 0) {
                        if ((whoIsFirst && result == 0) || (!whoIsFirst && result == 1)) {
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
                for (int i = 0; i<numberOfPlayers; ++i) {
                    System.out.print(" "+(i+1));
                }System.out.println();
                for (int i = 0; i<numberOfPlayers; ++i) {
                    System.out.print((i+1)+" ");
                    for (int j = 0; j<numberOfPlayers; ++j) {
                        if (i!=j) System.out.print(TableOfResults[i][j]+" ");
                        else System.out.print("# ");
                    }System.out.println();
                }
            }
        }
        return results;
    }

}
