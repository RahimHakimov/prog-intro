package expression.exceptions;

import expression.BitwiseXor;
import expression.MyExpression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class CheckedBitwiseXor extends BitwiseXor {

    public CheckedBitwiseXor(MyExpression first, MyExpression second) {
        super(first, second);
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        int res = super.resultOfOperation(x, y);
        if (res == Integer.MAX_VALUE || res == Integer.MIN_VALUE)
            throw new OverflowException();
        return res;
    }
}