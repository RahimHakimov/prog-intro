package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class ZeroDivisionException extends EvaluatingException {
    public ZeroDivisionException() {
        super("division by zero");
    }
}
