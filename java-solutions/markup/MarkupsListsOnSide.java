package markup;

import java.util.List;

public abstract class MarkupsListsOnSide extends MarkupsLists {
    String toMarkdownStr;
    String toHtmlStr;
    String toBBCodeStr;
    public MarkupsListsOnSide(List<Markup> s, String s1, String s2, String s3) {
        super(s);
        toMarkdownStr = s1;
        toHtmlStr = s2;
        toBBCodeStr = s3;
    }
    protected boolean isOnSide() {
        return true;
    }
    public void toMarkdown(StringBuilder s) {
        s.append(toMarkdownStr);
        toMarkdown_(s);
        s.append(toMarkdownStr);
    }

    public void toBBCode(StringBuilder s) {
        s.append("["+toBBCodeStr+"]");
        toBBCode_(s);
        s.append("[/"+toBBCodeStr+"]");
    }

    public void toHtml(StringBuilder s) {
        s.append("<"+toHtmlStr+">");
        toHtml_(s);
        s.append("</"+toHtmlStr+">");
    }
}
