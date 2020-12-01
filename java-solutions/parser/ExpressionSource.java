package parser;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public interface ExpressionSource {
    boolean hasNext();
    char next();
    ExpressionException error(final String message);
}
