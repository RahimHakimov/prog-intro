package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class MissingCloseParenthesis extends ParsingException {
    public MissingCloseParenthesis() {
        super("Source without closing parenthesis.");
    }
}