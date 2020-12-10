```
__current-repo\java-solutions\expression\parser\ExpressionParser.java:21: error: parse(String) in ExpressionParser cannot implement parse(String) in Parser
    public MyExpression parse(String expression) throws ParsingException {
                        ^
  overridden method does not throw ParsingException
__current-repo\java-solutions\expression\exceptions\ExpressionParser.java:9: error: parse(String) in ExpressionParser cannot implement parse(String) in Parser
public class ExpressionParser extends expression.parser.ExpressionParser implements Parser {
       ^
  overridden method does not throw ParsingException
2 errors

```
