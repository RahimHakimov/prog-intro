package expression;

import java.util.Map;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class PriorityOfOperations {

    public static final Map<Operation, Integer> PRIORITIES = Map.of(
            Operation.OR, 0,
            Operation.XOR, 1,
            Operation.AND, 2,
            Operation.ADD, 3, Operation.SUB, 3,
            Operation.MUL, 4, Operation.DIV, 4,
            Operation.CONST, 5, Operation.VAR, 5
    );

}
