package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Abs extends UnaryOperation {
    public Abs(MyExpression first) {
        super(first);
    }

    @Override
    protected int calculate(int x) {
        return (x >= 0) ? x : -x;
    }

    @Override
    protected String getOperationType() {
        return "abs";
    }

    @Override
    public int getPriority() {
        return Operation.PRIORITIES.get(Operation.ABS);
    }

    @Override
    public boolean isValuable() {
        return true;
    }

}
