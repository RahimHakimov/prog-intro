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

    protected boolean expect(final char excepted) {
        if (ch != excepted) {
            return false;
        }
        nextChar();
        return true;
    }

    protected boolean expect(final String excepted) {
        for (char c : excepted.toCharArray()) {
            if (!expect(c)) {
                return false;
            }
        }
        return true;
    }

    protected String getInfo() {
        return " Current part: " + source.partOfSource();
    }

    protected ParsingException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected void copyInteger(final StringBuilder sb) throws ParsingException {
        if (expect('-')) {
            sb.append('-');
        }
        if (expect('0')) {
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
        while (expect(' ') || expect('\r') || expect('\n') || expect('\t')) {
            // skip
        }
    }
}