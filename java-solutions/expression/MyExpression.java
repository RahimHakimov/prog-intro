package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public interface MyExpression extends Expression {
    int getPriority();
    boolean isValuable();
}