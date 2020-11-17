package game;

import game.player.HumanPlayer;
import game.player.Player;
import game.player.SequentialPlayer;

import java.util.List;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public class Main {
    public static void main(String[] args) {
        int n = 3, m = 3, k = 3;
        List<Player> players = List.of(new HumanPlayer(), new SequentialPlayer());
        final Game game = new Game(true, players);
        int result = game.play(new MNKBoard(m, n, k));
        if (result >= 0) {
            System.out.println("Player: " + (result + 1) + " won");
        } else {
            System.out.println("Draw!");
        }
        System.out.println("Result: " + (result + 1));
    }
}

