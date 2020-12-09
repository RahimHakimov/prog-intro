package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class OverflowException extends EvaluatingException {
    public OverflowException() {
        super("overflow");
    }
}