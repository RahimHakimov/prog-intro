package expression.exceptions;

import expression.MyExpression;
import expression.Negate;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class CheckedNegate extends Negate {
    public CheckedNegate(MyExpression first) {
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
