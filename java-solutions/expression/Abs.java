package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Abs extends UnaryOperation {
    public Abs(MyExpression first) {
        super(first);
    }

    @Override
    protected int resultOfOperation(int x) {
        return (x >= 0) ? x : -x;
    }

    @Override
    protected String getOperationString() {
        return "abs";
    }

}
