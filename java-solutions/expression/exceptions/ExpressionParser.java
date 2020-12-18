package expression.exceptions;

import expression.*;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class ExpressionParser extends BaseParser implements expression.exceptions.Parser {

    public ExpressionParser(StringSource stringSource) {
        super(stringSource, 2);
    }

    public ExpressionParser() {
        super(2);
    }

    @Override
    public MyExpression parse(String expression) throws ParsingException {
        changeSource(new StringSource(expression));
        MyExpression result = parseExpression();
        if (hasNext() && ch != '\0') {
            throw new MissingOpeningParenthesis(getParsingInfo());
        }
        return result;
    }

    public MyExpression parseExpression() throws ParsingException {
        skipWhitespace();
        return parseTerm(0);
    }

    private MyExpression parseTerm(int priority) throws ParsingException {
        skipWhitespace();
        if (priority == Operation.PRIORITIES.get(Operation.CONST)) {
            return parseValue();
        }
        MyExpression parsed = parseTerm(priority + 1);
        while (true) {
            Operation curOperation = getBinaryOperator(priority);
            if (curOperation == null) {
                return parsed;
            }
            parsed = buildBinaryOperation(parsed, parseTerm(priority + 1), curOperation);
        }
    }

    private MyExpression parseValue() throws ParsingException {
        skipWhitespace();
        if (test('(')) {
            MyExpression parsed = parseExpression();
            skipWhitespace();
            if (!expect(')')) {
                throw new MissingClosingParenthesis(getParsingInfo());
            }
            return parsed;
        } else if (test('-')) {
            if (between('0', '9')) {
                return parseConst(false);
            }
            return new Negate(parseValue());
        } else if (between('0', '9')) {
            return parseConst(true);
        } else {
            String unaryOperationOrVariable = parseToken();
            Operation operation = Operation.STRING_TO_UNARY.get(unaryOperationOrVariable);
            if (operation != null) {
                return buildUnaryOperation(parseValue(), operation);
            }
            return getVariable(unaryOperationOrVariable);
        }
    }

    private Operation getBinaryOperator(int priority) {
        skipWhitespace();
        for (Operation operation : Operation.PRIORITY_TO_OPER.get(priority)) {
            if (test(Operation.OPERATORS_STRING.get(operation))) {
                return operation;
            }
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

    private MyExpression getVariable(String token) throws InvalidVariableException {
        if (Operation.VARIABLES.contains(token)) {
            return new Variable(token);
        }
        throw new InvalidVariableException(token, getParsingInfo());
    }

    private MyExpression parseConst(boolean positive) throws ParsingException {
        final StringBuilder sb = new StringBuilder();
        if (!positive) {
            sb.append('-');
        }
        copyInteger(sb);
        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw new IllegalConstException(sb.toString(), getParsingInfo());
        }
    }

    private MyExpression buildUnaryOperation(MyExpression expr,
                                             Operation operation) {
        switch (operation) {
            case SQRT:
                return new CheckedSqrt(expr);
            case ABS:
                return new CheckedAbs(expr);
        }
        return null;
    }
}