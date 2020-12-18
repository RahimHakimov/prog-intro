import java.io.*;
import java.util.Arrays;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class DoublePalindrome {
    public static final int CONSTANT_MOD = 998244353;

    public static int plus(int x, int y) {
        return (int) ((long) x + y) % CONSTANT_MOD;
    }

    public static int minus(int x, int y) {
        return (int) ((long) x - y + CONSTANT_MOD) % CONSTANT_MOD;
    }

    public static int umn(int x, int y) {
        return (int) ((long) x * y % CONSTANT_MOD);
    }

    public static int step(int x, int y) {
        if (y == 0) {
            return 1;
        }
        if (y == 1) {
            return x % CONSTANT_MOD;
        }
        int cur = step(x, y / 2) % CONSTANT_MOD;
        int cur2 = umn(cur, cur) % CONSTANT_MOD;
        if (y % 2 == 1) return umn(cur2, x) % CONSTANT_MOD;
        return cur2;
    }

    public static void main(String[] args) throws Exception {
        MyScan in = new MyScan(System.in);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        in.hasNext();
        int n = in.nextNumber();
        int k = in.nextNumber();
        IntList ans = new IntList();
        IntList exact = new IntList();
        int answer = 0;
        for (int i = 1; i <= n; ++i) {
            if (i % 2 == 1) {
                ans.add(umn(i, step(k, (i + 1) / 2)));
            } else {
                ans.add(umn(i / 2, step(k, i / 2)));
                ans.set(i - 1, plus(ans.get(i - 1), umn(i / 2, step(k, i / 2 + 1))));
            }
            exact.add(ans.get(i - 1));
            for (int j = 1; j * j <= i; ++j) {
                if (i % j == 0 && j < i) {
                    ans.set(i - 1, minus(ans.get(i - 1), umn(exact.get(j - 1), i / j - 1)));
                    exact.set(i - 1, minus(exact.get(i - 1), umn(exact.get(j - 1), i / j)));
                    if (j * j < i && j != 1) {
                        int t = i / j;
                        ans.set(i - 1, minus(ans.get(i - 1), umn(exact.get(t - 1), i / t - 1)));
                        exact.set(i - 1, minus(exact.get(i - 1), umn(exact.get(t - 1), i / t)));
                    }
                }
            }
            answer = plus(answer, ans.get(i - 1));
        }
        out.write(answer + "\n");
        out.flush();
    }

    public static class MyScan {
        private final BufferedReader source;
        private final char[] buffer = new char[1024];
        private int currentIndex = 0;
        private int currentSize = 0;

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
                    char currentChar = buffer[currentIndex];
                    counter++;
                    currentIndex++;
                    currentString.append(currentChar);
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

    public static class IntList {
        int index = 0;
        int[] intList1;

        public IntList() {
            intList1 = new int[1];
        }

        public void add(int x) {
            if (index == intList1.length) {
                intList1 = Arrays.copyOf(intList1, intList1.length * 2);
            }
            intList1[index] = x;
            index++;
        }

        public int get(int x) {
            return intList1[x];
        }

        public void set(int ind, int x) {
            intList1[ind] = x;
        }

        public int size() {
            return index;
        }
    }
}
