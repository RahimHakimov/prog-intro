package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class IllegalConstException extends ParsingException {
    public IllegalConstException(String parsed, String message) {
        super("Illegal const: " + parsed + " " + message);
    }
}