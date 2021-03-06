package expression.exceptions;

import expression.*;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class ExpressionParser extends BaseParser implements expression.exceptions.Parser {

    private final int start = OperationsInfo.getStartPriority();
    private final int step = OperationsInfo.getStep();

    public ExpressionParser(StringSource stringSource) {
        super(stringSource);
    }

    public ExpressionParser() {
        super();
    }

    @Override
    public MyExpression parse(String expression) throws ParsingException {

        changeSource(new StringSource(expression));

        MyExpression result = parseExpression();

        if (!hasNext())
            return result;

        throw new MissingOpenParenthesis(source.getInfo());
    }

    public MyExpression parseExpression() throws ParsingException {

        skipWhitespace();

        return parseExpressionPart(start);
    }

    private MyExpression parseExpressionPart(int priority) throws ParsingException {

        skipWhitespace();

        if (priority == OperationsInfo.getPriority(Operation.VAR))
            return parseValue();

        MyExpression parsed = parseExpressionPart(priority + step);

        while (true) {
            Operation curOperation = getBinaryOperator(priority);

            if (curOperation == null)
                return parsed;

            parsed = buildBinaryOperation(parsed, parseExpressionPart(priority + step), curOperation);
        }
    }

    private MyExpression parseValue() throws ParsingException {

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

            if (!expect(')'))
                throw new MissingCloseParenthesis(source.getInfo());

            return parsed;
        } else {

            String parsed = parseOperationOrValue();
            Operation operation = OperationsInfo.getUnaryOperation(parsed);

            if (operation != null)
                switch (operation) {
                    case SQRT:
                        return new CheckedSqrt(parseValue());
                    case ABS:
                        return new CheckedAbs(parseValue());
                }

            return parseVariable(parsed);

        }
    }

    protected String parseOperationOrValue() {

        StringBuilder parsed = new StringBuilder();

        while (between('0', '9') || between('A', 'z')) {
            parsed.append(ch);
            nextChar();
        }

        return parsed.toString();
    }


    private MyExpression parseVariable(String variable) throws InvalidVariableException, MissedVariableException {

        if (OperationsInfo.checkVariable(variable))
            return new Variable(variable);

        if (variable.isEmpty())
            throw new MissedVariableException(source.getInfo());
        throw new InvalidVariableException(variable, source.getInfo());

    }

    private MyExpression parseConst(boolean negative) throws ParsingException {

        final StringBuilder sb = new StringBuilder();

        if (negative)
            sb.append('-');

        copyInteger(sb);

        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw new IllegalConstException(sb.toString());
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
                return new CheckedAdd(left, right);
            case SUB:
                return new CheckedSubtract(left, right);
            case MUL:
                return new CheckedMultiply(left, right);
            case DIV:
                return new CheckedDivide(left, right);
            case OR:
                return new CheckedBitwiseOr(left, right);
            case XOR:
                return new CheckedBitwiseXor(left, right);
            case AND:
                return new CheckedBitwiseAnd(left, right);
        }
        return null;
    }

}