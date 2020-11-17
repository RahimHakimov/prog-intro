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
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            Scanner in1 = new Scanner(in.nextLine());
            while (!in1.hasNext()) {in1 = new Scanner(in.nextLine());}
            int r = in1.nextInt();
            while (!in1.hasNext()) {in1 = new Scanner(in.nextLine());}
            int c = in1.nextInt();
            final Move move = new Move(r-1, c-1, cell);
            if (in1.hasNextInt()) {
                out.println("Please, enter only two numbers!!!");
            }
            else if (position.isValid(move)) {
                return move;
            } else {
                out.println("Move " + move + " is invalid");
            }
        }
    }
}
