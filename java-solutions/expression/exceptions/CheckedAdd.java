package expression.exceptions;

import expression.Add;
import expression.MyExpression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class CheckedAdd extends Add {

    public CheckedAdd(MyExpression first, MyExpression second) {
        super(first, second);
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        int res = super.resultOfOperation(x, y);
        if (y > 0 && x > 0 && res <= 0) {
            throw new OverflowException();
        }
        if (y < 0 && x < 0 && res >= 0) {
            throw new OverflowException();
        }
        return res;
    }
}