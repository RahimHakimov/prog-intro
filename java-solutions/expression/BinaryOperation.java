package expression;

/*
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

import java.util.Objects;

public abstract class BinaryOperation implements MyExpression {
    private final MyExpression first, second;
    private final String getOperationType;

    public BinaryOperation(MyExpression first, MyExpression second, String getOperationType) {
        this.first = first;
        this.second = second;
        this.getOperationType = getOperationType;
    }

    protected abstract int resultOfOperation(int x, int y);

    @Override
    public int evaluate(int x) {
        return resultOfOperation(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return resultOfOperation(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + first + " " + getOperationType + " " + second + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, this.getClass());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BinaryOperation) {
            BinaryOperation newSecond = (BinaryOperation) obj;
            return Objects.equals(this.getClass(), obj.getClass()) &&
                    Objects.equals(first, newSecond.first) && Objects.equals(second, newSecond.second);
        }
        return false;
    }
}