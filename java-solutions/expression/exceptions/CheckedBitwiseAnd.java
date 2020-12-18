package expression.exceptions;

import expression.BitwiseAnd;
import expression.MyExpression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class CheckedBitwiseAnd extends BitwiseAnd {

    public CheckedBitwiseAnd(MyExpression first, MyExpression second) {
        super(first, second);
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        return super.resultOfOperation(x, y);
    }

}