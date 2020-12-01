package expression.parser;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class StringSource implements ExpressionSource {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    public StringSource() {
        this.data = "";
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public ExpressionException error(final String message) {
        return new ExpressionException(pos + ": " + message);
    }
}
