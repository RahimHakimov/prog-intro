package expression.exceptions;

import expression.parser.StringSource;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class ExpressionParser extends expression.parser.ExpressionParser implements Parser {
    public ExpressionParser(StringSource stringSource) {
        super(stringSource);
    }

    public ExpressionParser() {
        super();
    }
}