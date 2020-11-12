package markup;

import java.util.List;

/**
 * @author Rakhim Khakimov
 */


public class Strikeout extends MarkupsListsOnSide {
    public Strikeout(List<Markup> s) {
        super(s, "~", "s", "s");
    }
}
