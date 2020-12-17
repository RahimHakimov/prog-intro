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
    protected int resultOfOperation(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return super.resultOfOperation(x);
    }
}
