package game.player;

import game.Cell;
import game.Move;
import game.Position;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            try {
                out.println("Position");
                out.println(position);
                out.println(cell + "'s move");
                out.println("Enter row and column");
                final Move move = new Move(in.nextInt(), in.nextInt(), cell);
                if (position.isValid(move)) {
                    return move;
                }
                out.println("Move " + move + " is invalid");
            } catch (Exception e) {
                return new Move(-1, -1, cell);
            }
        }
    }
}