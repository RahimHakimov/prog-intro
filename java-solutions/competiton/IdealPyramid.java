import java.io.*;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class IdealPyramid {
    public static void main(String[] args) throws Exception {
        MyScan in = new MyScan(System.in);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        in.hasNext();
        int n = in.nextNumber();
        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int x = in.nextNumber();
            int y = in.nextNumber();
            int h = in.nextNumber();
            if (x - h < minX) {
                minX = x - h;
            }
            if (x + h > maxX) {
                maxX = x + h;
            }
            if (y - h < minY) {
                minY = y - h;
            }
            if (y + h > maxY) {
                maxY = y + h;
            }
        }
        long maxx = maxY - minY;
        if (maxX - minX > maxx) {
            maxx = maxX - minX;
        }
        out.write((maxX + minX) / 2 + " " + (maxY + minY) / 2 + " " + (maxx + 1) / 2 + "\n");
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
