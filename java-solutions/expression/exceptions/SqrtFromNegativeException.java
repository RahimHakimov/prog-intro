package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class SqrtFromNegativeException extends EvaluatingException {
    public SqrtFromNegativeException() {
        super("Sqrt from negative");
    }
}
