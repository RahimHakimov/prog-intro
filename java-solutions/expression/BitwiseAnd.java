package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class BitwiseAnd extends BinaryOperation {

    public BitwiseAnd(MyExpression first, MyExpression second) {
        super(first, second, "&");
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        return x & y;
    }

    @Override
    public int getPriority() {
        return Operation.PRIORITIES.get(Operation.AND);
    }

    @Override
    public boolean isValuable() {
        return false;
    }
}