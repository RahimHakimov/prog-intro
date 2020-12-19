package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class MissingOpenParenthesis extends ParsingException {
    public MissingOpenParenthesis(String message) {
        super("Closing parentheses without opening. " + message);
    }
}