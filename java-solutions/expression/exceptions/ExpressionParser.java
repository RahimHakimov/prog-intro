package expression.exceptions;

import expression.*;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class ExpressionParser extends BaseParser implements expression.exceptions.Parser {

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
        if (hasNext() && ch != '\0') {
            throw new MissingOpenParenthesis(getInfo());
        }
        return result;
    }

    public MyExpression parseExpression() throws ParsingException {
        skipWhitespace();
        return parseExpressionPart(0);
    }

    private MyExpression parseExpressionPart(int priority) throws ParsingException {
        skipWhitespace();
        if (priority == InformationAboutOperations.PRIORITIES.get(Operation.CONST)) {
            return parseValue();
        }
        MyExpression parsed = parseExpressionPart(priority + 1);
        while (true) {
            Operation curOperation = getBinaryOperator(priority);
            if (curOperation == null) {
                return parsed;
            }
            parsed = buildBinaryOperation(parsed, parseExpressionPart(priority + 1), curOperation);
        }
    }

    private MyExpression parseValue() throws ParsingException {
        skipWhitespace();
        if (test('(')) {
            MyExpression parsed = parseExpression();
            skipWhitespace();
            if (!expect(')')) {
                throw new MissingCloseParenthesis(getInfo());
            }
            return parsed;
        } else if (test('-')) {
            if (between('0', '9')) {
                return parseConst(true);
            }
            return new Negate(parseValue());
        } else if (between('0', '9')) {
            return parseConst(false);
        } else {
            String parsedToken = parseToken();
            Operation operation = InformationAboutOperations.STRING_TO_UNARY_OPERATION.get(parsedToken);
            if (operation != null) {
                return buildUnaryOperation(parseValue(), operation);
            }
            return getVariable(parsedToken);
        }
    }

    private Operation getBinaryOperator(int priority) {
        skipWhitespace();
        for (Operation operation : InformationAboutOperations.PRIORITY_TO_OPERATION.get(priority)) {
            if (test(InformationAboutOperations.OPERATORS_STRING.get(operation))) {
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

    private MyExpression getVariable(String variable) throws InvalidVariableException {
        if (InformationAboutOperations.VARIABLES.contains(variable)) {
            return new Variable(variable);
        }
        throw new InvalidVariableException(variable, getInfo());
    }

    private MyExpression parseConst(boolean negative) throws ParsingException {
        final StringBuilder sb = new StringBuilder();
        if (negative) {
            sb.append('-');
        }
        copyInteger(sb);
        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw new IllegalConstException(sb.toString(), getInfo());
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