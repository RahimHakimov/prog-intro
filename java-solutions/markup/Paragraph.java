package markup;

import java.util.List;

/**
 * @author Rakhim Khakimov
 */


public class Paragraph extends MarkupsLists {
    public Paragraph(List<Markup> s) {
        super(s);
    }
    protected boolean isOnSide() {
        return false;
    }
    public void toMarkdown(StringBuilder s) {
        toMarkdown_(s);
    }
    public void toBBCode(StringBuilder s) {
        toBBCode_(s);
    }
    public void toHtml(StringBuilder s) {
        toHtml_(s);
    }
}
