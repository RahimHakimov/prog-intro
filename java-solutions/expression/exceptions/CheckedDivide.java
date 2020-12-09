package expression.exceptions;

import expression.Divide;
import expression.MyExpression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class CheckedDivide extends Divide {

    public CheckedDivide(MyExpression first, MyExpression second) {
        super(first, second);
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
}