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

}