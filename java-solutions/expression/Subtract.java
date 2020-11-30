package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class Subtract extends BinaryOperation {

    public Subtract(MyExpression first, MyExpression second) {
        super(first, second, "-");
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        return x - y;
    }

    @Override
    public int getPriority() {
        return Operation.PRIORITIES.get(Operation.SUB);
    }

    @Override
    public boolean isValuable() {
        return true;
    }
}