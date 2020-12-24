package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class MissedVariableException extends ParsingException {
    public MissedVariableException(String info) {
        super("Missed variable there: " + info);
    }
}