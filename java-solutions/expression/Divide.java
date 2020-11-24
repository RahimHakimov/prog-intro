package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class Divide extends BinaryOperation {

    public Divide(MyExpression first, MyExpression second) {
        super(first, second);
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        return x / y;
    }

    @Override
    protected String getOperationType() {
        return "/";
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public boolean isValuable() {
        return true;
    }
}