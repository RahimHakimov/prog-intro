package expression.exceptions;

import expression.BinaryOperation;
import expression.MyExpression;
import expression.Operation;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class CheckedAdd extends BinaryOperation {

    public CheckedAdd(MyExpression first, MyExpression second) {
        super(first, second, "+");
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        int res = x + y;
        if (y > 0 && x > 0 && res <= 0) {
            throw new OverflowException();
        }
        if (y < 0 && x < 0 && res >= 0) {
            throw new OverflowException();
        }
        return res;
    }

    @Override
    public int getPriority() {
        return Operation.PRIORITIES.get(Operation.ADD);
    }

    @Override
    public boolean isValuable() {
        return false;
    }
}