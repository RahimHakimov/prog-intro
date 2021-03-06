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

        int maxToOverflow = Integer.MAX_VALUE;
        if ((x < 0 && y > 0) ||
                (x > 0 && y < 0))
            maxToOverflow = Integer.MIN_VALUE;

        if ((x == -1 && y == Integer.MIN_VALUE) ||
                (y == -1 && x == Integer.MIN_VALUE) ||
                (x != 0 && y < 0 && y < maxToOverflow / x) ||
                (x != -1 && x != 0 && y > 0 && y > maxToOverflow / x)) {
            throw new OverflowException();
        }

        return super.resultOfOperation(x, y);
    }
}