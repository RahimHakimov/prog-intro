package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class BitwiseOr extends BinaryOperation {

    public BitwiseOr(MyExpression first, MyExpression second) {
        super(first, second, "|");
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        return x | y;
    }

    @Override
    public int getPriority() {
        return Operation.PRIORITIES.get(Operation.OR);
    }

    @Override
    public boolean isValuable() {
        return false;
    }
}