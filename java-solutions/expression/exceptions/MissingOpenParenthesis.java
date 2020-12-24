package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class MissingOpenParenthesis extends ParsingException {
    public MissingOpenParenthesis(String info) {
        super("Closing parentheses without opening. There:"+info);
    }
}