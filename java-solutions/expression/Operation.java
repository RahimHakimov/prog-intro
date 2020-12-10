package expression;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public enum Operation {
    OR, XOR, AND,
    ADD, SUB,
    MUL, DIV,
    ABS, SQRT,
    CONST, VAR;

    public static final Map<Operation, Integer> PRIORITIES = Map.ofEntries(Map.entry(OR, 0), Map.entry(XOR, 1), Map.entry(AND, 2), Map.entry(ADD, 3), Map.entry(SUB, 3), Map.entry(MUL, 4), Map.entry(DIV, 4), Map.entry(ABS, 5), Map.entry(SQRT, 5), Map.entry(CONST, 6), Map.entry(VAR, 6));

    public static final Map<Operation, String> OPERATORS_STRING = Map.ofEntries(
            Map.entry(ADD, "+"), Map.entry(SUB, "-"),
            Map.entry(MUL, "*"), Map.entry(DIV, "/"),
            Map.entry(OR, "|"), Map.entry(XOR, "^"),
            Map.entry(AND, "&"), Map.entry(ABS, "abs"), Map.entry(SQRT, "sqrt")
    );

    public static final Set<String> VARIABLES = Set.of(
            "x", "y", "z"
    );

    public static final List<List<Operation>> PRIORITY_TO_OPER = List.of(
            List.of(OR),
            List.of(XOR),
            List.of(AND),
            List.of(ADD, SUB),
            List.of(MUL, DIV),
            List.of(ABS, SQRT),
            List.of(CONST, VAR)
    );

    public static final Map<String, Operation> STRING_TO_UNARY = Map.of(
            "sqrt", SQRT, "abs", ABS
    );

    public static final Map<Character, Operation> CHAROPERANDS = Map.of(
            '&', AND, '|', OR, '^', XOR,
            '+', ADD, '-', SUB,
            '*', MUL, '/', DIV
    );

}
