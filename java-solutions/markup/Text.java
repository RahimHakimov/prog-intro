package markup;

/**
 * @author Rakhim Khakimov
 */


public class Text implements Markup {
    String text;

    public Text(String s) {
        text = s;
    }

    public void toMarkdown(StringBuilder s) {
        s.append(text);
    }

    public void toBBCode(StringBuilder s) {
        s.append(text);
    }

    public void toHtml(StringBuilder s) {
        s.append(text);
    }
}
