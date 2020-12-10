package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class InvalidOperatorException extends ParsingException {
    public InvalidOperatorException(String cur, String message) {
        super("Invalid operator: '" + cur + "'. " + message);
    }
}