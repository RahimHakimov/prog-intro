package expression.parser;

import expression.*;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class ExpressionParser extends BaseParser implements Parser {

    private final int start = OperationsInfo.getStartPriority();
    private final int step = OperationsInfo.getStep();

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

        return parseExpressionPart(start);
    }

    private MyExpression parseExpressionPart(int priority) {

        skipWhitespace();

        if (priority == OperationsInfo.getPriority(Operation.CONST))
            return parseValue();

        MyExpression parsed = parseExpressionPart(priority + step);

        while (true) {
            Operation curOperation = getBinaryOperator(priority);

            if (curOperation == null)
                return parsed;

            parsed = buildBinaryOperation(parsed, parseExpressionPart(priority + step), curOperation);
        }
    }

    private MyExpression parseValue() {

        skipWhitespace();

        if (between('0', '9'))
            return parseConst(false);
        else if (test('-')) {

            if (between('0', '9'))
                return parseConst(true);

            return new Negate(parseValue());
        } else if (test('(')) {

            MyExpression parsed = parseExpression();
            skipWhitespace();

            expect(')');

            return parsed;
        } else
            return parseVariable();
    }

    private MyExpression parseVariable() {

        skipWhitespace();

        final String variable = Character.toString(ch);
        nextChar();

        return new Variable(variable);
    }

    private MyExpression parseConst(boolean negative) {
        final StringBuilder sb = new StringBuilder();

        if (negative)
            sb.append('-');

        copyInteger(sb);

        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw new AssertionError("Invalid number " + sb);
        }
    }

    private Operation getBinaryOperator(int priority) {

        skipWhitespace();

        for (Operation operation : OperationsInfo.getOperationFromPriority(priority)) {
            char operator = OperationsInfo.getBinaryOperator(operation);
            if (test(operator))
                return operation;
        }
        return null;
    }


    private MyExpression buildBinaryOperation(MyExpression left, MyExpression right,
                                              Operation operation) {
        switch (operation) {
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
