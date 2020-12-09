package expression.exceptions;

import expression.BinaryOperation;
import expression.MyExpression;
import expression.Operation;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class CheckedMultiply extends BinaryOperation {

    public CheckedMultiply(MyExpression first, MyExpression second) {
        super(first, second, "*");
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        int res = x * y;
        if (x != 0 && y != 0 && (res / y != x || res / x != y)) {
            throw new OverflowException();
        }
        return res;
    }

    @Override
    public int getPriority() {
        return Operation.PRIORITIES.get(Operation.MUL);
    }

    @Override
    public boolean isValuable() {
        return false;
    }
}