package expression.parser;

import expression.exceptions.ParsingException;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public interface ExpressionSource {
    boolean hasNext();

    char next();

    ParsingException error(final String message);
}
