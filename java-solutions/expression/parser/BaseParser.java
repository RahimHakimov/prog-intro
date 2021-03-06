package expression.parser;

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

    public BaseParser() {
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

    protected boolean test(char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected void expect(final char c) {
        if (ch != c)
            throw error("Expected '" + c + "', found '" + ch + "'");

        nextChar();
    }

    protected ExpressionException error(final String message) {
        return source.error(message);
    }

    protected void copyInteger(final StringBuilder sb) {
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

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
            // skip
        }
    }

}
