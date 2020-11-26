package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Main {
    public static void main(String[] args) {
        int X = Integer.parseInt(args[0]);
        Variable x = new Variable("x");
        System.out.println(new Add(new Subtract(new Multiply(x, x), new Multiply(new Const(2), x)), new Const(1)).evaluate(X));
    }
}