package expression.parser;

import expression.*;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class ExpressionParser extends BaseParser implements Parser {
    public ExpressionParser(StringSource stringSource) {
        super(stringSource);
    }

    public ExpressionParser() {
        super();
    }

    @Override
    public TripleExpression parse(String expression) {
        changeSource(new StringSource(expression));
        return parseExpression();
    }

    public MyExpression parseExpression() {
        skipWhitespace();
        return parseExpressionPart(0);
    }

    private MyExpression parseExpressionPart(int priority) {
        skipWhitespace();
        if (priority == InformationAboutOperations.PRIORITIES.get(Operation.CONST)) {
            return parseValue();
        }

        MyExpression parsed = parseExpressionPart(priority + 1);

        while (true) {
            skipWhitespace();
            final Operation curOperation = InformationAboutOperations.CHAR_OPERANDS.get(ch);
            if (curOperation == null || priority != InformationAboutOperations.PRIORITIES.get(curOperation)) {
                return parsed;
            }
            nextChar();
            parsed = buildBinaryOperation(parsed, parseExpressionPart(priority + 1), curOperation);
        }
    }

    private MyExpression parseValue() {
        if (test('(')) {
            MyExpression parsed = parseExpression();
            skipWhitespace();
            expect(')');
            return parsed;
        } else if (test('-')) {
            skipWhitespace();
            if (between('0', '9')) {
                return parseConst(true);
            }
            return new Negate(parseValue());
        } else if (between('0', '9')) {
            return parseConst(false);
        } else {
            return parseVariable();
        }
    }

    private MyExpression parseVariable() {
        skipWhitespace();
        final String variable = Character.toString(ch);
        nextChar();
        return new Variable(variable);
    }

    private MyExpression parseConst(boolean negative) {
        final StringBuilder sb = new StringBuilder();
        if (negative) {
            sb.append('-');
        }
        copyInteger(sb);
        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw new AssertionError("Invalid number " + sb);
        }
    }

    private MyExpression buildBinaryOperation(MyExpression left, MyExpression right,
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
            case AND:
                return new BitwiseAnd(left, right);
            case XOR:
                return new BitwiseXor(left, right);
            case OR:
                return new BitwiseOr(left, right);
        }
        return null;
    }

}
