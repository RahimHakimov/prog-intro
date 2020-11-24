package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class Subtract extends BinaryOperation {

    public Subtract(MyExpression first, MyExpression second) {
        super(first, second);
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        return x - y;
    }

    @Override
    protected String getOperationType() {
        return "-";
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean isValuable() {
        return true;
    }
}