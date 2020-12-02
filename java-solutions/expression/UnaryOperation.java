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

    protected abstract int calculate(int x);

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(cur.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return calculate(cur.evaluate(x));
    }

    @Override
    public String toString() {
        return "(" + getOperationType() + cur + ")";
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();

        if (this.isValuable()) {
            sb.append("(").append(getOperationType()).append(cur.toMiniString()).append(")");
        } else {
            sb.append(getOperationType()).append(cur.toMiniString());
        }
        return sb.toString();
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
