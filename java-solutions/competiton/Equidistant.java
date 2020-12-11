import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Equidistant {
    public static List<List<Integer>> rebr = new ArrayList<>();
    public static int[] depth;
    public static int[] IntArray;

    public static void dfs(int command, int x, int deep) {
        if (command == depth.length) {
            return;
        }
        depth[command] = deep;
        IntArray[command] = x;
        for (int vert : rebr.get(command)) {
            if (vert != x) {
                dfs(vert, command, deep + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MyScan in = new MyScan(System.in);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        in.hasNext();
        int n = in.nextNumber();
        int m = in.nextNumber();
        for (int i = 0; i < n; ++i) {
            List<Integer> t = new ArrayList<>();
            rebr.add(t);
        }
        for (int i = 0; i < n - 1; ++i) {
            int u = in.nextNumber() - 1;
            int v = in.nextNumber() - 1;
            rebr.get(u).add(v);
            rebr.get(v).add(u);
        }
        int[] commands = new int[m];
        for (int i = 0; i < m; ++i) {
            commands[i] = in.nextNumber() - 1;
        }
        depth = new int[n];
        IntArray = new int[n];
        dfs(commands[0], commands[0], 0);
        int deep = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < m; ++i) {
            if (deep < depth[commands[i]]) {
                index = commands[i];
                deep = depth[commands[i]];
            }
        }
        int deepest = depth[index];
        if (deepest % 2 == 1) {
            System.out.println("NO");
            return;
        }
        int middle = index;
        for (int i = 0; i < deepest / 2; ++i) {
            middle = IntArray[middle];
        }
        dfs(middle, middle, 0);
        for (int j = 0; j < commands.length; ++j) {
            if (depth[commands[j]] != deepest / 2) {
                System.out.println("NO");
                return;
            }
        }
        out.write("YES\n");
        out.write(middle + 1 + "\n");
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
