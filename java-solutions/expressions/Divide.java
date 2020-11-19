package expressions;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Divide extends Operations{
    public Divide(Expression a, Expression b) {
        super(a, b);
    }

    public OpType opType() {
        return OpType.Divide;
    }

    public int FirstOpSecond(int a, int b) {
        return a/b;
    }

    @Override
    public int getDeep() {
        return 0;
    }

    public String View() {
        return "/";
    }
}
