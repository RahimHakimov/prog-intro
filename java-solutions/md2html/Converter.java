package md2html;

import java.util.Map;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Converter {
    private final Map<String, String> md2htmlTags = Map.of("*", "em", "_", "em",
            "**", "strong", "__", "strong",
            "`", "code", "--", "s");
    private final Map<Character, String> htmlSymbols = Map.of('<', "&lt;",
            '>', "&gt;", '&', "&amp;");
    private int ind;
    private final StringBuilder result;

    public Converter(String paragraph) {
        result = new StringBuilder();
        ind = 0;
        final int headerLevel = getHeaderLevel(paragraph);
        if (headerLevel > 0) {
            result.append("<h").append(headerLevel).append(">");
            ind = headerLevel + 1;
        } else {
            result.append("<p>");
        }
        nextTag(paragraph, result, "");
        if (headerLevel > 0) {
            result.append("</h").append(headerLevel).append(">");
        } else {
            result.append("</p>");
        }
    }

    public StringBuilder convert() {
        return result;
    }

    private int getHeaderLevel(String line) {
        int headerLevel = 0;
        while (headerLevel < line.length() && line.charAt(headerLevel) == '#') {
            headerLevel++;
        }
        if (headerLevel < line.length() && line.charAt(headerLevel) == ' ') {
            return headerLevel;
        }
        return 0;
    }

    private StringBuilder nextTag(String line, StringBuilder resLine, String lastTag) {
        String mdTag = "";
        String htmlTag = "";
        while (ind < line.length()) {
            char curChar = line.charAt(ind);
            switch (curChar) {
                case '`':
                    mdTag = Character.toString(curChar);
                    htmlTag = md2htmlTags.get(mdTag);
                    break;
                case '*':
                case '_':
                    if (ind + 1 < line.length() && !lastTag.equals(Character.toString(curChar)) &
                            line.charAt(ind + 1) == curChar) {
                        mdTag = line.substring(ind, ind + 2);
                        ind++;
                    } else {
                        mdTag = line.substring(ind, ind + 1);
                    }
                    htmlTag = md2htmlTags.get(mdTag);
                    break;
                case '\\':
                    if (ind + 1 < line.length() &&
                            md2htmlTags.get(Character.toString(line.charAt(ind + 1))) != null) {
                        ind++;
                    }
                    resLine.append(line.charAt(ind));
                    break;
                case '-':
                    if (ind + 1 < line.length()
                            && line.charAt(ind + 1) == '-') {
                        mdTag = "--";
                        ind++;
                        htmlTag = md2htmlTags.get(mdTag);
                        break;
                    }
                default:
                    String htmlSymbol = htmlSymbols.getOrDefault(curChar, String.valueOf(curChar));
                    resLine.append(htmlSymbol);
            }

            if (!mdTag.isEmpty() && mdTag.equals(lastTag)) {
                resLine.append("</").append(htmlTag).append(">");
                return resLine;
            }
            ind++;
            if (!mdTag.isEmpty()) {
                StringBuilder editedLine = nextTag(line, new StringBuilder(), mdTag);
                if (editedLine.length() > htmlTag.length() &&
                        editedLine.substring(editedLine.length() - htmlTag.length() - 1,
                                editedLine.length() - 1).equals(htmlTag)) {
                    resLine.append("<").append(htmlTag).append(">").append(editedLine);
                    ind++;
                } else {
                    if (!mdTag.equals("[")) {
                        resLine.append(mdTag);
                    }
                    resLine.append(editedLine);
                }
                mdTag = "";
            }
        }
        return resLine;
    }
}