package expression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public interface TripleExpression extends ToMiniString {
    int evaluate(int x, int y, int z);
}
