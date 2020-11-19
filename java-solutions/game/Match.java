package game;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Match {
    public final Player player1;
    public final Player player2;
    public boolean log;
    public int numOfWins1 = 0;
    public int numOfWins2 = 0;
    public final int numberOfGames;


    public Match(final boolean log, Player player1, Player player2, int numberOfGames) {
        this.player1 = player1;
        this.player2 = player2;
        this.numberOfGames = numberOfGames;
        this.log = log;
    }

    public int play(Board board) {
        int numOfGame = 0;
        while (Math.max(numOfWins1, numOfWins2) < numberOfGames) {
            Game game;
            if (numOfGame % 2 == 0) {
                game = new Game(log, new Player[]{player1, player2});
            } else {
                game = new Game(log, new Player[]{player2, player1});
            }
            int result = game.play(board);
            if (result >= 0) {
                if ((numOfGame % 2 == 0 && result == 0) || (numOfGame % 2 != 0 && result == 1)) {
                    numOfWins1++;
                } else {
                    numOfWins2++;
                }
            }
            numOfGame++;
        }
        if (numOfWins1 >= numberOfGames) return 0;
        else return 1;
    }

}
