import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Managing {
    public static void main(String[] args) throws Exception {
        MyScan in = new MyScan(System.in);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        in.hasNext();
        int T = in.nextNumber();
        for (int temp = 0; temp < T; temp++) {
            final Map<Integer, Integer> cnts = new HashMap<>();
            int n = in.nextNumber();
            int[] a = new int[n];
            for (int i = 0; i < n; ++i) {
                a[i] = in.nextNumber();
            }
            int answer = 0;
            for (int i = n; i > 0; --i) {
                for (int j = 0; j < i - 1; ++j) {
                    answer += cnts.getOrDefault(2 * a[i - 1] - a[j], 0);
                }
                if (cnts.containsKey(a[i - 1])) {
                    cnts.put(a[i - 1], cnts.get(a[i - 1]) + 1);
                } else {
                    cnts.put(a[i - 1], 1);
                }
            }
            out.write(answer + "\n");
            out.flush();
        }
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