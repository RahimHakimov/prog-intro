package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class Multiply extends BinaryOperation {

    public Multiply(MyExpression first, MyExpression second) {
        super(first, second, "*");
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        return x * y;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public boolean isValuable() {
        return false;
    }
}