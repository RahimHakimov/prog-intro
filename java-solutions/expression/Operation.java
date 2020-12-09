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
    CONST, VAR;

    public static final Map<Operation, Integer> PRIORITIES = Map.of(
            OR, 0,
            XOR, 1,
            AND, 2,
            ADD, 3, SUB, 3,
            MUL, 4, DIV, 4,
            CONST, 5, VAR, 5
    );

    public static final Map<Operation, String> OPERATORS_STRING = Map.of(
            ADD, "+", SUB, "-",
            MUL, "*", DIV, "/",
            OR, "|", XOR, "^",
            AND, "&"
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
            List.of(CONST, VAR)
    );
}
