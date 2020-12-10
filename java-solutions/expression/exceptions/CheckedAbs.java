package expression.exceptions;

import expression.Abs;
import expression.MyExpression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class CheckedAbs extends Abs {
    public CheckedAbs(MyExpression first) {
        super(first);
    }

    @Override
    protected int calculate(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        if (x == Integer.MAX_VALUE) {
            throw new OverflowException();
        }
        return (x >= 0) ? x : -x;
    }
}
