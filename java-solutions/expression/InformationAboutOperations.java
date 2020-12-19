package expression;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class InformationAboutOperations {
    public static final Map<Operation, Integer> PRIORITIES = Map.ofEntries(Map.entry(Operation.OR, 0), Map.entry(Operation.XOR, 1), Map.entry(Operation.AND, 2), Map.entry(Operation.ADD, 3), Map.entry(Operation.SUB, 3), Map.entry(Operation.MUL, 4), Map.entry(Operation.DIV, 4), Map.entry(Operation.ABS, 5), Map.entry(Operation.SQRT, 5), Map.entry(Operation.CONST, 6), Map.entry(Operation.VAR, 6));

    public static final Map<Operation, String> OPERATORS_STRING = Map.ofEntries(
            Map.entry(Operation.ADD, "+"), Map.entry(Operation.SUB, "-"),
            Map.entry(Operation.MUL, "*"), Map.entry(Operation.DIV, "/"),
            Map.entry(Operation.OR, "|"), Map.entry(Operation.XOR, "^"),
            Map.entry(Operation.AND, "&"), Map.entry(Operation.ABS, "abs"), Map.entry(Operation.SQRT, "sqrt")
    );

    public static final Set<String> VARIABLES = Set.of(
            "x", "y", "z"
    );

    public static final List<List<Operation>> PRIORITY_TO_OPERATION = List.of(
            List.of(Operation.OR),
            List.of(Operation.XOR),
            List.of(Operation.AND),
            List.of(Operation.ADD, Operation.SUB),
            List.of(Operation.MUL, Operation.DIV),
            List.of(Operation.ABS, Operation.SQRT),
            List.of(Operation.CONST, Operation.VAR)
    );

    public static final Map<String, Operation> STRING_TO_UNARY_OPERATION = Map.of(
            "sqrt", Operation.SQRT, "abs", Operation.ABS
    );

    public static final Map<Character, Operation> CHAR_OPERANDS = Map.of(
            '&', Operation.AND, '|', Operation.OR, '^', Operation.XOR,
            '+', Operation.ADD, '-', Operation.SUB,
            '*', Operation.MUL, '/', Operation.DIV
    );

}
