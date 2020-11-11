package markup;

import java.util.*;

/**
 * @author Rakhim Khakimov
 */


public class Strong extends MarkupsListsOnSide {
    public Strong(List<Markup> s) {
        super(s, "__", "strong", "b");
    }
}
