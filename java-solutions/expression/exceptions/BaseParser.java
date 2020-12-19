package expression.exceptions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class BaseParser {
    protected final char END_OF_SOURCE = '\0';
    private final char[] buffer;
    protected char ch;
    private ExpressionSource source;
    private int head = 0;
    private int size = 0;
    private int position = 0;

    protected BaseParser(final ExpressionSource source) {
        this.source = source;
        buffer = new char[2002];
        bufferUpdate();
    }

    protected BaseParser() {
        buffer = new char[2002];
    }

    private void bufferUpdate() {
        while (source.hasNext() && size < 2002) {
            buffer[(head + size) % 2002] = source.next();
            size++;
        }
        ch = size > 0 ? buffer[head % 2002] : END_OF_SOURCE;
    }

    protected void changeSource(final ExpressionSource source) {
        this.source = source;

        size = 0;
        head = 0;

        bufferUpdate();
    }

    protected void nextChar() {
        if (size > 0) {
            position++;
            size--;
            head = (head + 1) % 2002;
            ch = buffer[(head + 1) % 2002];
            bufferUpdate();
        } else {
            ch = END_OF_SOURCE;
        }
    }

    protected boolean hasNext() {
        return size > 0;
    }

    protected boolean test(char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected boolean test(String expected) {
        bufferUpdate();
        if (size >= expected.length()) {
            int ind = 0;
            while (ind < expected.length()) {
                if (expected.charAt(ind) != buffer[(head + ind) % 2002]) {
                    return false;
                }
                ind++;
            }
            head = (head + ind) % 2002;
            size -= ind;
            bufferUpdate();
            return true;
        }
        return false;
    }

    protected boolean expect(final char c) {
        if (ch != c) {
            return false;
        }
        nextChar();
        return true;
    }

    protected boolean expect(final String value) {
        for (char c : value.toCharArray()) {
            if (!expect(c)) {
                return false;
            }
        }
        return true;
    }

    protected String getInfo() {
        return "Current pos: " + position + " Current part: " + source.partOfSource();
    }

    protected ParsingException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected void copyInteger(final StringBuilder sb) throws ParsingException {
        if (test('-')) {
            sb.append('-');
        }
        if (test('0')) {
            sb.append('0');
        } else if (between('1', '9')) {
            while (between('0', '9')) {
                sb.append(ch);
                nextChar();
            }
        } else {
            throw error("Invalid number");
        }
    }

    protected void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
            // skip
        }
    }
}