package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Add extends BinaryOperation {

    public Add(MyExpression first, MyExpression second) {
        super(first, second, "+");
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        return x + y;
    }

}