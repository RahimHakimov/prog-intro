package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Sqrt extends UnaryOperation {
    public Sqrt(MyExpression first) {
        super(first);
    }

    @Override
    protected int calculate(int x) {
        int i;
        for (i = 0; i * i <= x; ++i) ;
        return i - 1;
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
