package expression.exceptions;

import expression.BinaryOperation;
import expression.MyExpression;
import expression.Operation;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class CheckedBitwiseXor extends BinaryOperation {

    public CheckedBitwiseXor(MyExpression first, MyExpression second) {
        super(first, second, "^");
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        return x ^ y;
    }

    @Override
    public int getPriority() {
        return Operation.PRIORITIES.get(Operation.XOR);
    }

    @Override
    public boolean isValuable() {
        return false;
    }
}