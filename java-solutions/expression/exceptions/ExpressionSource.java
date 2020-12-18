package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public interface ExpressionSource {
    boolean hasNext();

    char next();

    ParsingException error(final String message);

    String partOfSource();
}
