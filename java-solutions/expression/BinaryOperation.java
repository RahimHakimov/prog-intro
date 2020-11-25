package expression;

/*
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

import java.util.Objects;

public abstract class BinaryOperation implements MyExpression {
    private final MyExpression first, second;

    public BinaryOperation(MyExpression first, MyExpression second) {
        this.first = first;
        this.second = second;
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
        return "(" + first + " " + getOperationType() + " " + second + ")";
    }

    private boolean requireBrackets(MyExpression expr) {
        return second.getPriority() < this.getPriority() ||
                second.getPriority() == this.getPriority() &&
                        (second.isValuable() || this.isValuable());
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        MyExpression op = this.first;
        termToString(sb, op, op.getPriority() < this.getPriority());
        sb.append(" ").append(getOperationType()).append(" ");
        termToString(sb, second, requireBrackets(second));
        return sb.toString();
    }

    private void termToString(StringBuilder sb, MyExpression op, boolean brackets) {
        if (brackets) {
            sb.append("(").append(op.toMiniString()).append(")");
        } else {
            sb.append(op.toMiniString());
        }
    }

    protected abstract String getOperationType();

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