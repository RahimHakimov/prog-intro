package markup;

import java.util.*;

/**
 * @author Rakhim Khakimov
 */


public class Emphasis extends MarkupsListsOnSide {
    public Emphasis(List<Markup> s) {
        super(s, "*", "em", "i");
    }
}
