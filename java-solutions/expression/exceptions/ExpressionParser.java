package expression.exceptions;

import expression.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class ExpressionParser extends BaseParser implements expression.exceptions.Parser {

    private static final List<List<Operation>> PRIORITY_TO_OPERATION = List.of(
            List.of(Operation.OR),
            List.of(Operation.XOR),
            List.of(Operation.AND),
            List.of(Operation.ADD, Operation.SUB),
            List.of(Operation.MUL, Operation.DIV),
            List.of(Operation.CONST, Operation.VAR)
    );

    private static final Map<Operation, String> OPERATION_TO_STRING = Map.of(
            Operation.OR, "|",
            Operation.XOR, "^",
            Operation.AND, "&",
            Operation.ADD, "+", Operation.SUB, "-",
            Operation.MUL, "*", Operation.DIV, "/"
    );

    private static final Set<String> VARIABLES = Set.of(
            "x", "y", "z"
    );

    private static final Map<String, Operation> STRING_TO_UNARY_OPERATION = Map.of(
            "sqrt", Operation.SQRT, "abs", Operation.ABS
    );

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
        if (hasNext() && ch != END_OF_SOURCE) {
            throw new MissingOpenParenthesis();
        }
        return result;
    }

    public MyExpression parseExpression() throws ParsingException {
        skipWhitespace();
        return parseExpressionPart(0);
    }

    private MyExpression parseExpressionPart(int priority) throws ParsingException {
        skipWhitespace();
        if (priority == PriorityOfOperations.PRIORITIES.get(Operation.CONST)) {
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
        if (between('0', '9')) {
            return parseConst(false);
        } else if (test('-')) {
            if (between('0', '9')) {
                return parseConst(true);
            }
            return new Negate(parseValue());
        } else if (test('(')) {
            MyExpression parsed = parseExpression();
            skipWhitespace();
            if (!expect(')')) {
                throw new MissingCloseParenthesis();
            }
            return parsed;
        } else {
            String parsedToken = parseOperationOrValue();
            Operation operation = STRING_TO_UNARY_OPERATION.get(parsedToken);
            if (operation != null) {
                return buildUnaryOperation(parseValue(), operation);
            }
            return parseVariable(parsedToken);
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


    private MyExpression parseVariable(String variable) throws InvalidVariableException {
        if (!VARIABLES.contains(variable))
            throw new InvalidVariableException(variable);
        return new Variable(variable);
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
            throw new IllegalConstException(sb.toString());
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

    private Operation getBinaryOperator(int priority) {
        skipWhitespace();
        for (Operation operation : PRIORITY_TO_OPERATION.get(priority)) {
            String operator = OPERATION_TO_STRING.get(operation);
            boolean check = true;
            for (char c : operator.toCharArray()) {
                if (!test(c)) {
                    check = false;
                    break;
                }
            }
            if (check)
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