package expression.exceptions;

import expression.BinaryOperation;
import expression.BitwiseXor;
import expression.MyExpression;
import expression.Operation;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class CheckedBitwiseXor extends BitwiseXor {

    public CheckedBitwiseXor(MyExpression first, MyExpression second) {
        super(first, second);
    }
    @Override
    protected int resultOfOperation(int x, int y) {
        return x ^ y;
    }
}