package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Sqrt extends UnaryOperation {
    public Sqrt(MyExpression first) {
        super(first);
    }

    @Override
    protected int resultOfOperation(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int l = 1;
        int r = x / 2;
        while (r - l >= 0) {
            int m = l + (r - l) / 2;
            if (m == x / m) {
                return m;
            }
            if (m < x / m) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return l - 1;
    }

    @Override
    protected String getOperationType() {
        return "sqrt";
    }

    @Override
    public int getPriority() {
        return Operation.PRIORITIES.get(Operation.SQRT);
    }

    @Override
    public boolean isValuable() {
        return true;
    }

}
