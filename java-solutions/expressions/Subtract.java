package expressions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Subtract extends Operations{
    public Subtract(Expression a, Expression b) {
        super(a, b);
    }

    public OpType opType() {
        return OpType.Subtract;
    }

    public int FirstOpSecond(int a, int b) {
        return a-b;
    }

    @Override
    public int getDeep() {
        return 0;
    }
    public String View() {
        return "-";
    }
}
