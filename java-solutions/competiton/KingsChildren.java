import java.io.*;
import java.util.Arrays;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class KingsChildren {
    public static char[][] a;

    public static void connect(int U, int B, int L, int R) {
        for (int i = U + 1; i < B; ++i) {
            for (int j = L; j < R; ++j) {
                if (a[i][j] == '.') {
                    a[i][j] = Character.toLowerCase(a[i - 1][j]);
                }
            }
        }
        for (int i = B - 2; i > U - 1; --i) {
            for (int j = L; j < R; ++j) {
                if (a[i][j] == '.') {
                    a[i][j] = Character.toLowerCase(a[i + 1][j]);
                }
            }
        }
        for (int i = U; i < B; ++i) {
            for (int j = L + 1; j < R; ++j) {
                if (a[i][j] == '.') {
                    a[i][j] = Character.toLowerCase(a[i][j - 1]);
                }
            }
        }
        for (int i = U; i < B; ++i) {
            for (int j = R - 2; j > L - 1; --j) {
                if (a[i][j] == '.') {
                    a[i][j] = Character.toLowerCase(a[i][j + 1]);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MyScan in = new MyScan(System.in);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        in.hasNext();
        int n = in.nextNumber();
        int m = in.nextNumber();
        a = new char[n][m];
        int aX = -1, aY = -1;
        for (int i = 0; i < n; ++i) {
            String cur = in.nextWord();
            for (int j = 0; j < m; ++j) {
                a[i][j] = cur.charAt(j);
                if (a[i][j] == 'A') {
                    aX = i;
                    aY = j;
                }
            }
        }
        a[aX][aY] = '.';
        int maxSize = -1;
        int bL = -1;
        int bR = -1;
        int bU = -1;
        int bB = -1;
        int[] upperChar = new int[m + 1];
        for (int i = 0; i < n; ++i) {
            IntPairList s = new IntPairList();
            for (int j = 0; j < m + 1; ++j) {
                if (j == m || a[i][j] != '.') {
                    upperChar[j] = i + 1;
                }
                int x = j;
                while (s.size() > 0 && s.getSecond(s.size() - 1) < upperChar[j]) {
                    int L = s.getFirst(s.size() - 1);
                    int R = j;
                    int U = s.getSecond(s.size() - 1);
                    int B = i + 1;
                    if ((U <= aX && aX < B) && (L <= aY && aY < R)) {
                        if ((R - L) * (B - U) > maxSize) {
                            maxSize = (R - L) * (B - U);
                            bL = L;
                            bR = R;
                            bU = U;
                            bB = B;
                        }
                    }
                    x = s.getFirst(s.size() - 1);
                    s.removeLast();
                }
                s.add(x, upperChar[j]);
            }
        }
        for (int i = bU; i < bB; ++i) {
            for (int j = bL; j < bR; ++j) {
                a[i][j] = 'a';
            }
        }
        connect(0, bU, 0, m);
        connect(bB, n, 0, m);
        connect(bU, bB, 0, bL);
        connect(bU, bB, bR, m);
        a[aX][aY] = 'A';
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                out.write(a[i][j]);
            }
            out.write("\n");
        }
        out.flush();
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

    public static class IntPairList {
        int index = 0;
        int[] intList1;
        int[] intList2;

        public IntPairList() {
            intList1 = new int[1024];
            intList2 = new int[1024];
        }

        public void add(int x, int y) {
            if (index == intList1.length) {
                intList1 = Arrays.copyOf(intList1, intList1.length * 2);
                intList2 = Arrays.copyOf(intList2, intList2.length * 2);
            }
            intList1[index] = x;
            intList2[index] = y;
            index++;
        }

        public int getFirst(int x) {
            return intList1[x];
        }

        public int getSecond(int x) {
            return intList2[x];
        }

        public int size() {
            return index;
        }

        public void removeLast() {
            index--;
        }
    }
}