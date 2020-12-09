package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class InvalidVariableException extends ParsingException {
    public InvalidVariableException(String variable, String message) {
        super("Invalid or missed variable: " + variable + " " + message);
    }
}