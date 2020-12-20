package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class BitwiseAnd extends BinaryOperation {

    public BitwiseAnd(MyExpression first, MyExpression second) {
        super(first, second, "&");
    }

    @Override
    protected int resultOfOperation(int x, int y) {
        return x & y;
    }

}