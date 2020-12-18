import java.io.*;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class AccurateMovement {
    public static void main(String[] args) throws Exception {
        MyScan in = new MyScan(System.in);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        in.hasNext();
        int a = in.nextNumber();
        int b = in.nextNumber();
        int n = in.nextNumber();
        out.write((1 + (n - a - 1) / (b - a) * 2) + "\n");
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