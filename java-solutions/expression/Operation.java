package expression;

import java.util.Map;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public enum Operation {
    ADD, SUB,
    MUL, DIV,
    CONST, VAR,
    AND, OR, XOR;

    public static final Map<Operation, Integer> PRIORITIES = Map.of(
            OR, 0, XOR, 1, AND, 2,
            ADD, 3, SUB, 3,
            MUL, 4, DIV, 4,
            CONST, 5, VAR, 5
    );

    public static final Map<Character, Operation> CHAROPERANDS = Map.of(
            '&', AND, '|', OR, '^', XOR,
            '+', ADD, '-', SUB,
            '*', MUL, '/', DIV
    );

}
