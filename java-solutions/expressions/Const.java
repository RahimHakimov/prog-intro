package expressions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Const implements Expression{
    private final int NUMBER;

    public Const(int k) {
        NUMBER = k;
    }

    public String toString() {
        return Integer.toString(NUMBER);
    }

    public int evaluate(int x) {
        return NUMBER;
    }

    public String toMiniString() {
        return Integer.toString(NUMBER);
    }

    public Type TYPE() {
        return Type.CONST;
    }

    public int getNUMBER() {
        return NUMBER;
    }

    public int getDeep() {
        return 0;
    }

    public boolean equals(Expression expression) {
        if (expression.TYPE() != TYPE()) return false;
        Const EXP = (Const) expression;
        return getNUMBER() == EXP.getNUMBER();
    }
}
