package expression.exceptions;

import expression.MyExpression;
import expression.UnaryOperation;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class CheckedNegate extends UnaryOperation {
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

    @Override
    protected String getOperationType() {
        return "-";
    }

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public boolean isValuable() {
        return true;
    }

}
