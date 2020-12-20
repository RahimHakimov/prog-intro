package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Negate extends UnaryOperation {
    public Negate(MyExpression first) {
        super(first);
    }

    @Override
    protected int resultOfOperation(int x) {
        return -x;
    }

    @Override
    protected String getOperationString() {
        return "-";
    }

}
