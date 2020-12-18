package md2html;

import java.util.Map;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Converter {
    private final Map<String, String> MD_2_HTML = Map.of("*", "em", "_", "em",
            "**", "strong", "__", "strong",
            "`", "code", "--", "s", "~", "mark");
    private final Map<Character, String> HTML_SYMBOLS = Map.of('<', "&lt;",
            '>', "&gt;", '&', "&amp;");
    private final StringBuilder answer = new StringBuilder();
    private int curIndex = 0;

    public Converter(StringBuilder paragraph) {
        int headerLevel = 0;
        while (headerLevel < paragraph.length() && paragraph.charAt(headerLevel) == '#') {
            headerLevel++;
        }
        if (headerLevel >= paragraph.length() || paragraph.charAt(headerLevel) != ' ') {
            headerLevel = 0;
        }

        if (headerLevel > 0) {
            answer.append("<h").append(headerLevel).append(">");
            curIndex = headerLevel + 1;
        } else {
            answer.append("<p>");
        }

        next(paragraph, answer, "");

        if (headerLevel > 0) {
            answer.append("</h").append(headerLevel).append(">");
        } else {
            answer.append("</p>");
        }
    }

    private void next(StringBuilder line, StringBuilder answer, String lastTag) {
        String mdTag = "";
        String htmlTag = "";
        while (curIndex < line.length()) {

        }
    }

    public String convert() {
        return answer.toString();
    }


}
