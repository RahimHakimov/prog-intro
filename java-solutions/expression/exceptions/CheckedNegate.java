package expression.exceptions;

import expression.MyExpression;
import expression.Negate;
import expression.UnaryOperation;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class CheckedNegate extends Negate {
    public CheckedNegate(MyExpression first) {
        super(first);
    }

    @Override
    protected int calculate(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -x;
    }
}
