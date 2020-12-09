package expression.exceptions;

import expression.BinaryOperation;
import expression.MyExpression;
import expression.Operation;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class CheckedDivide extends BinaryOperation {

    public CheckedDivide(MyExpression first, MyExpression second) {
        super(first, second, "/");
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        if (y == 0) {
            throw new ZeroDivisionException();
        }
        if (y == -1 && x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return x / y;
    }

    @Override
    public int getPriority() {
        return Operation.PRIORITIES.get(Operation.DIV);
    }

    @Override
    public boolean isValuable() {
        return true;
    }
}