package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class MissingOpeningParenthesis extends ParsingException {
    public MissingOpeningParenthesis(String message) {
        super("Unexpected closing parentheses without opening. " + message);
    }
}