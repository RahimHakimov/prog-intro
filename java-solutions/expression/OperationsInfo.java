package expression;

import java.util.List;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class OperationsInfo {

    private static final int start = 5;
    private static final int step = -1;

    public static int getStartPriority() {
        return start;
    }

    public static int getStep() {
        return step;
    }

    public static int getPriority(Operation operation) {
        switch (operation) {
            case OR:
                return start;
            case XOR:
                return start+step;
            case AND:
                return start+2*step;
            case ADD:
            case SUB:
                return start+3*step;
            case MUL:
            case DIV:
                return start+4*step;
            case SQRT:
            case ABS:
            case VAR:
            case CONST:
                return start+5*step;
            default:
                return start+6*step;
        }
    }

    public static boolean checkVariable(String variable) {
        switch (variable) {
            case "x":
            case "y":
            case "z":
                return true;
            default:
                return false;
        }
    }

    public static List<Operation> getOperationFromPriority(int priority) {
        switch (priority) {
            case start:
                return List.of(Operation.OR);
            case start+step:
                return List.of(Operation.XOR);
            case start+2*step:
                return List.of(Operation.AND);
            case start+3*step:
                return List.of(Operation.ADD, Operation.SUB);
            case start+4*step:
                return List.of(Operation.MUL, Operation.DIV);
            default:
                return null;
        }
    }

    public static char getBinaryOperator(Operation operation) {
        switch (operation) {
            case OR:
                return '|';
            case XOR:
                return '^';
            case AND:
                return '&';
            case ADD:
                return '+';
            case SUB:
                return '-';
            case MUL:
                return '*';
            case DIV:
                return '/';
            default:
                return '\0';
        }
    }

    public static Operation getUnaryOperation(String operator) {
        switch (operator) {
            case "abs":
                return Operation.ABS;
            case "sqrt":
                return Operation.SQRT;
            default:
                return null;
        }
    }
}
