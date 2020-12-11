import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class HighLoadDataBase {
    public static void main(String[] args) throws Exception {
        MyScan in = new MyScan(System.in);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        in.hasNext();
        int n = in.nextNumber();
        int[] a = new int[n];
        int MAX = 0;
        for (int i = 0; i < n; ++i) {
            a[i] = in.nextNumber();
            MAX = Math.max(MAX, a[i]);
        }
        int q = in.nextNumber();
        int[] b = new int[n];
        b[0] = a[0];
        for (int i = 1; i < n; ++i) {
            b[i] = b[i - 1] + a[i];
        }
        Map<Integer, Integer> answer = new HashMap<>();
        for (int i = 0; i < q; ++i) {
            int t = in.nextNumber();
            if (t < MAX) {
                out.write("Impossible\n");
                continue;
            }
            if (answer.containsKey(t)) {
                out.write(answer.get(t) + "\n");
                continue;
            }
            int ans = 0;
            int cur = 0;
            while (cur < n) {
                int l = cur;
                int r = n;
                while (r - l > 1) {
                    int m = (l + r) / 2;
                    if ((cur > 0 && (b[m] - b[cur - 1]) <= t) || (b[m] <= t)) {
                        l = m;
                    } else {
                        r = m;
                    }
                }
                cur = r;
                ans++;
            }
            answer.put(t, ans);
            out.write(ans + "\n");
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
}
