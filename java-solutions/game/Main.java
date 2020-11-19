package game;

import game.player.HumanPlayer;
import game.player.Player;
import game.player.RandomPlayer;
import game.player.SequentialPlayer;

import java.util.List;
import java.util.Scanner;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public class Main {
    public static void main(String[] args) {
        int n, m, k, c, numberOfPlayers, numberOfGamesInOneMatch;
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter sides of board(m, n) and numer of equal signs to win(k):");
        n = s.nextInt();
        m = s.nextInt();
        k = s.nextInt();
        System.out.println("Please, enter number of cycles of one tournament");
        c = s.nextInt();
        System.out.println("Please, enter number of players");
        numberOfPlayers = s.nextInt();
        System.out.println("Please, enter number of games to win in one match");
        numberOfGamesInOneMatch = s.nextInt();
        Player[] players = new Player[numberOfPlayers];
        System.out.println("Please, choose players!" +
                "\nEnter 1 to HumanPlayer\nEnter 2 to RandomPlayer\nEnter 3 to SequentialPlayer\n");
        int ind = 0;
        while (ind < numberOfPlayers) {
            System.out.println("Please, enter type of player:");
            int x = s.nextInt();
            if (x == 1) players[ind] = new HumanPlayer();
            else if (x == 2) players[ind] = new RandomPlayer();
            else if (x == 3) players[ind] = new SequentialPlayer();
            else {
                System.out.println("You had some mistake, try again!!!");
                continue;
            }
            ind++;
        }
        final Tournament tournament = new Tournament(true, numberOfPlayers, players);
        int[] results = tournament.whoIsTheBestPlayer(new MNKBoard(m, n, k), c, numberOfGamesInOneMatch);
        for (int i = 0; i < numberOfPlayers; ++i) {
            System.out.println((i + 1) + " " + results[i]);
        }
    }
}

