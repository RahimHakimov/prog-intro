import java.io.*;
import java.util.*;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class CrossStitch {
    public static Map<CrsStitch, List<CrsStitch>> frontSide = new HashMap<>();
    public static Map<CrsStitch, List<CrsStitch>> backSide = new HashMap<>();
    public static List<CrsStitch> answer = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        new solution();
    }

    public static class CrsStitch {
        int row, clmn;

        public CrsStitch(int r, int c) {
            row = r;
            clmn = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CrsStitch crsStitch = (CrsStitch) o;
            return row == crsStitch.row &&
                    clmn == crsStitch.clmn;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, clmn);
        }
    }

    public static class solution {
        public solution() throws Exception {
            MyScan in = new MyScan(System.in);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            in.hasNext();
            int w = in.nextNumber();
            int h = in.nextNumber();
            char[][] pat = new char[h][w];
            CrsStitch first = null;
            for (int i = 0; i < h; ++i) {
                String cur = in.nextWord();
                for (int j = 0; j < w; ++j) {
                    pat[i][j] = cur.charAt(j);
                    if (pat[i][j] == 'X') {
                        CrsStitch coord1 = new CrsStitch(i, j);
                        CrsStitch coord2 = new CrsStitch(i + 1, j + 1);
                        check(coord1, coord2, true);

                        CrsStitch coord3 = new CrsStitch(i, j + 1);
                        CrsStitch coord4 = new CrsStitch(i + 1, j);
                        check(coord3, coord4, true);

                        CrsStitch coord5 = new CrsStitch(i, j);
                        CrsStitch coord6 = new CrsStitch(i + 1, j);
                        check(coord5, coord6, false);

                        CrsStitch coord7 = new CrsStitch(i, j + 1);
                        CrsStitch coord8 = new CrsStitch(i + 1, j + 1);
                        check(coord7, coord8, false);
                        first = new CrsStitch(i, j);
                    }
                }
            }
            Do(first, true);
            out.write(answer.size() - 1 + "\n");
            for (CrsStitch cur : answer) {
                out.write(cur.clmn + " " + cur.row + "\n");
            }
            out.flush();
        }

        void Do(CrsStitch temp, boolean whichSide) {
            Map<CrsStitch, List<CrsStitch>> side;
            if (whichSide) {
                side = frontSide;
            } else {
                side = backSide;
            }
            List<CrsStitch> sttchs = side.get(temp);
            while (sttchs.size() > 0) {
                CrsStitch cur = sttchs.remove(sttchs.size() - 1);
                side.get(cur).remove(temp);
                Do(cur, !whichSide);
                answer.add(temp);
            }
        }

        void check(CrsStitch coord1, CrsStitch coord2, boolean whichSide) {
            if (whichSide) {
                if (!frontSide.containsKey(coord1))
                    frontSide.put(coord1, new ArrayList<>());
                frontSide.get(coord1).add(coord2);
                if (!frontSide.containsKey(coord2))
                    frontSide.put(coord2, new ArrayList<>());
                frontSide.get(coord2).add(coord1);
            } else {
                if (!backSide.containsKey(coord1))
                    backSide.put(coord1, new ArrayList<>());
                backSide.get(coord1).add(coord2);
                if (!backSide.containsKey(coord2))
                    backSide.put(coord2, new ArrayList<>());
                backSide.get(coord2).add(coord1);
            }
        }
    }

    public static class MyScan {
        private final BufferedReader source;
        private int currentIndex = 0;
        private int currentSize = 0;
        private final char[] buffer = new char[1024];

        public MyScan(InputStream input) {
            source = new BufferedReader(new InputStreamReader(input));
        }

        boolean hasNext() throws IOException {
            while (currentSize <= currentIndex && currentSize != -1) {
                currentSize = source.read(buffer);
                currentIndex = 0;
            }
            return (currentSize > currentIndex);
        }

        public boolean isEndOfLine(boolean isWord) throws IOException {
            while (Character.isWhitespace(buffer[currentIndex])) {
                if (buffer[currentIndex++] == '\n') {
                    return true;
                }
                hasNext();
            }
            return false;

        }

        private String next() throws IOException {
            StringBuilder currentString = new StringBuilder();
            int counter = 0;
            while (currentIndex < currentSize) {
                if (!Character.isWhitespace(buffer[currentIndex])) {
                    //char currentChar = ;
                    counter++;
                    currentString.append(buffer[currentIndex]);
                    currentIndex++;
                    hasNext();
                } else if (counter != 0) {
                    break;
                } else {
                    currentIndex++;
                    if (currentIndex >= currentSize) {
                        if (!hasNext()) {
                            break;
                        }
                    }
                }
            }
            return currentString.toString();
        }

        public String nextWord() throws IOException {
            return next();
        }

        public int nextNumber() throws Exception {
            String temp = next();
            return Integer.parseInt(temp);
        }
    }
}
