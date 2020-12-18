package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class MissingClosingParenthesis extends ParsingException {
    public MissingClosingParenthesis(String message) {
        super("Source without closing parenthesis. " + message);
    }
}