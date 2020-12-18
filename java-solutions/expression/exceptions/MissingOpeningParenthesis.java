package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class MissingOpeningParenthesis extends ParsingException {
    public MissingOpeningParenthesis(String message) {
        super("Closing parentheses without opening. " + message);
    }
}