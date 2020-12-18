package expression.exceptions;

import expression.BitwiseOr;
import expression.MyExpression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class CheckedBitwiseOr extends BitwiseOr {

    public CheckedBitwiseOr(MyExpression first, MyExpression second) {
        super(first, second);
    }

}