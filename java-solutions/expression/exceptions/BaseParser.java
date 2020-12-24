package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class BaseParser {
    protected final char END_OF_SOURCE = '\0';
    protected char ch;
    private ExpressionSource source;

    protected BaseParser(final ExpressionSource source) {
        this.source = source;
        nextChar();
    }

    protected BaseParser() {
    }

    protected void changeSource(final ExpressionSource source) {
        this.source = source;
        nextChar();
    }

    protected void nextChar() {
        ch = source.hasNext() ? source.next() : END_OF_SOURCE;
    }

    protected boolean hasNext() {
        return ch != END_OF_SOURCE;
    }

    protected boolean test(char c) {
        if (ch != c) {
            return false;
        }

        nextChar();
        return true;
    }

    protected boolean expect(final char excepted) {
        if (ch != excepted) {
            return false;
        }

        nextChar();
        return true;
    }

    protected ParsingException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected void copyInteger(final StringBuilder sb) throws ParsingException {
        if (test('-'))
            sb.append('-');

        if (test('0'))
            sb.append('0');
        else if (between('1', '9')) {

            while (between('0', '9')) {
                sb.append(ch);
                nextChar();
            }

        } else
            throw error("Invalid number");

    }

    protected void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
            // skip
        }
    }

}