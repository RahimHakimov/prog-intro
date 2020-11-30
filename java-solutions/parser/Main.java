package parser;

import expression.MyExpression;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser(new StringSource("x* (x - 2)*x + 1"));
        MyExpression result = parser.parseExpression();
        System.out.println();
    }
}
