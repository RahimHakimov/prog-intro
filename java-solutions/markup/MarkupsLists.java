package markup;

import java.util.List;

public abstract class MarkupsLists implements Markup {
    protected List<Markup> text;

    public MarkupsLists(List<Markup> s) {
        text = s;
    }

    protected void toMarkdown_(StringBuilder s) {
        for (Markup cur : text) {
            cur.toMarkdown(s);
        }
    }

    protected void toBBCode_(StringBuilder s) {
        for (Markup cur : text) {
            cur.toBBCode(s);
        }
    }

    protected void toHtml_(StringBuilder s) {
        for (Markup cur : text) {
            cur.toHtml(s);
        }
    }

    protected abstract boolean isOnSide();
}
