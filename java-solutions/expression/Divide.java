package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Divide extends BinaryOperation {

    public Divide(MyExpression first, MyExpression second) {
        super(first, second, "/");
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        return x / y;
    }

}