import java.io.*;

public class JustTheLastDigit {
    public static void main(String[] args) throws Exception {
        MyScan in = new MyScan(System.in);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        in.hasNext();
        int n = in.nextNumber();
        int[][] counter = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s = in.nextWord();
            for (int j = 0; j < s.length(); j++) {
                counter[i][j] = (s.charAt(j) - '0') % 2;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (counter[i][j] > 0) {
                    for (int k = j + 1; k < n; k++) {
                        int x = counter[i][k] ^ counter[j][k];
                        counter[i][k] = x;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.write("" + counter[i][j]);
            }
            out.write("\n");
        }
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
}
