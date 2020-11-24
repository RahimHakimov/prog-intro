package expression;

import java.util.Objects;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public final class Variable implements MyExpression {
    private final String var;

    public Variable(String var) {
        this.var = var;
    }
    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int getPriority() {
        return 2020;
    }

    @Override
    public boolean isValuable() {
        return false;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable) {
            Variable second = (Variable) obj;
            return Objects.equals(var, second.var);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(var);
    }
}