package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Negate extends UnaryOperation {
    public Negate(MyExpression first) {
        super(first);
    }

    @Override
    protected int calculate(int x) {
        return -x;
    }

    @Override
    protected String getOperationType() {
        return "-";
    }

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public boolean isValuable() {
        return true;
    }

}
