package expression.exceptions;

import expression.Multiply;
import expression.MyExpression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class CheckedMultiply extends Multiply {

    public CheckedMultiply(MyExpression first, MyExpression second) {
        super(first, second);
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        int res = super.resultOfOperation(x, y);
        if (x != 0 && y != 0 && (res / y != x || res / x != y)) {
            throw new OverflowException();
        }
        return res;
    }
}