package expression;

import java.util.Objects;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public abstract class UnaryOperation implements MyExpression {
    protected final MyExpression cur;

    public UnaryOperation(MyExpression cur) {
        this.cur = cur;
    }

    protected abstract int resultOfOperation(int x);

    @Override
    public int evaluate(int x, int y, int z) {
        return resultOfOperation(cur.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return resultOfOperation(cur.evaluate(x));
    }

    @Override
    public String toString() {
        return "(" + getOperationType() + cur + ")";
    }

    protected abstract String getOperationType();

    @Override
    public int hashCode() {
        return Objects.hash(cur, getOperationType());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnaryOperation) {
            UnaryOperation newOperation = (UnaryOperation) obj;
            return Objects.equals(cur, newOperation.cur)
                    && Objects.equals(getOperationType(), newOperation.getOperationType());
        }
        return false;
    }
}
