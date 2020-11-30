package expression;

import java.util.Map;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public interface MyExpression extends TripleExpression, Expression {
    int getPriority();

    boolean isValuable();
}