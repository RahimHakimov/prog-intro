package game;

import game.player.HumanPlayer;
import game.player.Player;
import game.player.RandomPlayer;
import game.player.SequentialPlayer;

import java.util.List;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public class Main {
    public static void main(String[] args) {
        int n = 5, m = 5, k = 4, c = 2, numberOfPlayers = 3, numberOfGamesInOneMatch = 2;
        Player[] players = new Player[]{new HumanPlayer(), new HumanPlayer(), new SequentialPlayer()};
        final Tournament tournament = new Tournament(true, numberOfPlayers, players);
        int[] results = tournament.whoIsTheBestPlayer(new MNKBoard(m, n, k), c, numberOfGamesInOneMatch);
        for (int i = 0; i < numberOfPlayers; ++i) {
            System.out.println((i + 1) + " " + results[i]);
        }
    }
}

