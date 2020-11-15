package game;

import game.player.HumanPlayer;
import game.player.SequentialPlayer;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public class Main {
    public static void main(String[] args) {
        final Game game = new Game(false, new HumanPlayer(), new SequentialPlayer());
        int result;
        /*do {*/
        result = game.play(new MNKBoard(5, 5, 5));
        System.out.println("Game result: " + result);
        /*} while (result != 0);*/
    }
}
