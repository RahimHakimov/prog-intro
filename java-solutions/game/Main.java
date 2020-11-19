package game;

import game.player.HumanPlayer;
import game.player.RandomPlayer;
import game.player.SequentialPlayer;

import java.util.Scanner;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public class Main {
    public static Scanner s;
    public static int checkInputNext() {
        int n = -1;
        while (n <= 0) {
            String t = s.next();
            try {
                n = Integer.parseInt(t);
            } catch (Exception e) {
                System.out.println("Please, enter NUMBER, greater than 0!!! enter number!!!");
            }
        }
        return n;
    }
    public static void main(String[] args) {
        int n, m, k, c, numberOfPlayers, numberOfGamesInOneMatch;
        s = new Scanner(System.in);
        System.out.println("Please enter sides of board(m, n) and numer of equal signs to win(k):");
        n = checkInputNext();
        m = checkInputNext();
        k = checkInputNext();
        System.out.println("Please, enter number of cycles of one tournament");
        c = checkInputNext();
        System.out.println("Please, enter number of players");
        numberOfPlayers = checkInputNext();
        System.out.println("Please, enter number of games to win in one match");
        numberOfGamesInOneMatch = checkInputNext();
        Player[] players = new Player[numberOfPlayers];
        System.out.println("Please, choose players!" +
                "\nEnter 1 to HumanPlayer\nEnter 2 to RandomPlayer\nEnter 3 to SequentialPlayer\n");
        int ind = 0;
        while (ind < numberOfPlayers) {
            System.out.println("Please, enter type of player"+(ind+1)+":");
            String x = s.next();
            switch (x) {
                case "1" -> players[ind] = new HumanPlayer();
                case "2" -> players[ind] = new RandomPlayer();
                case "3" -> players[ind] = new SequentialPlayer();
                default -> {
                    System.out.println("You had some mistake, try again!!!");
                    continue;
                }
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

