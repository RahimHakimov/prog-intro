package expression.exceptions;

import expression.BitwiseOr;
import expression.MyExpression;
import expression.Operation;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class CheckedBitwiseOr extends BitwiseOr {

    public CheckedBitwiseOr(MyExpression first, MyExpression second) {
        super(first, second);
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        return x | y;
    }
}