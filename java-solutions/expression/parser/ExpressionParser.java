package expression.parser;

import expression.*;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class ExpressionParser extends BaseParser implements Parser{
    public ExpressionParser(StringSource stringSource) {
        super(stringSource);
    }

    public ExpressionParser() {
        super();
    }

    @Override
    public TripleExpression parse(String expression) {
        changeSource(new StringSource(expression));
        nextChar();
        return parseExpression();
    }

    public MyExpression parseExpression() {
        skipWhitespace();
        return parseTerm(0);
    }

    private MyExpression parseTerm(int priority) {
        skipWhitespace();
        if (priority == Operation.PRIORITIES.get(Operation.CONST)) {
            return parseValue();
        }

        MyExpression parsed = parseTerm(priority + 1);

        while (true) {
            skipWhitespace();
            final Operation curOperation = Operation.CHAROPERANDS.get(ch);
            if (curOperation == null || priority != Operation.PRIORITIES.get(curOperation)) {
                return parsed;
            }
            nextChar();
            parsed = buildOperation(parsed, parseTerm(priority + 1), curOperation);
        }
    }

    private MyExpression parseValue() {
        if (test('(')) {
            MyExpression parsed = parseExpression();
            skipWhitespace();
            expect(')');
            return parsed;
        }else if (test('-')) {
            skipWhitespace();
            if (between('0', '9')) {
                return parseConst(false);
            }
            return new UnaryMinus(parseValue());
        } else if (between('0', '9')) {
            return parseConst(true);
        } else {
            return parseVariable();
        }
    }

    private MyExpression buildOperation(MyExpression left, MyExpression right,
                                            Operation oper) {
        switch (oper) {
            case ADD:
                return new Add(left, right);
            case SUB:
                return new Subtract(left, right);
            case MUL:
                return new Multiply(left, right);
            case DIV:
                return new Divide(left, right);
        }
        return null;
    }

    private MyExpression parseVariable() {
        skipWhitespace();
        final String variable = Character.toString(ch);
        nextChar();
        return new Variable(variable);
    }

    private MyExpression parseConst(boolean positive) {
        final StringBuilder sb = new StringBuilder();
        if (!positive) {
            sb.append('-');
        }
        copyInteger(sb);
        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw new AssertionError("Invalid number " + sb);
        }
    }
}
