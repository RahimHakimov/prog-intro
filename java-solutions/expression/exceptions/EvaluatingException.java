package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class EvaluatingException extends RuntimeException {
    public EvaluatingException(String message) {
        super(message);
    }
}