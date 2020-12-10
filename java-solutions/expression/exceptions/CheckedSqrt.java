package expression.exceptions;

import expression.MyExpression;
import expression.Sqrt;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class CheckedSqrt extends Sqrt {
    public CheckedSqrt(MyExpression first) {
        super(first);
    }

    @Override
    protected int calculate(int x) {
        if (x < 0) {
            throw new SqrtFromNegativeException();
        }
        int i;
        for (i = 0; i * i <= x; ++i) ;
        return i - 1;
    }
}
