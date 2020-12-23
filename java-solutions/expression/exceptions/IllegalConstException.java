package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class IllegalConstException extends ParsingException {
    public IllegalConstException(String parsed) {
        super("Illegal const: " + parsed);
    }
}