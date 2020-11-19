package expressions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Variable implements Expression{
    private final String X;

    public Variable(String x) {
        X = x;
    }

    public String toString() {
        return X;
    }

    public int evaluate(int x) {
        return x;
    }

    public String toMiniString() {
        return X;
    }

    public Type TYPE() {
        return Type.VARIABLE;
    }

    public int getDeep() {
        return 0;
    }

    public boolean equals(Expression expression) {
        if (expression.TYPE() != TYPE()) return false;
        return true;
    }
}
