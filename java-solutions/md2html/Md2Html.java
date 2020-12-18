package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Rakhim Khakimov (ramhakimov@niuitmo.ru)
 */

public class Md2Html {
    public static void main(String[] args) {
        String inp = args[0];
        String outp = args[1];
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(inp)), StandardCharsets.UTF_8))) {
            try (BufferedWriter out = new BufferedWriter(new FileWriter(outp, StandardCharsets.UTF_8))) {
                String line = in.readLine();
                while (line != null) {
                    StringBuilder paragraph = new StringBuilder();
                    while (line.isEmpty()) {
                        line = in.readLine();
                    }
                    while (line != null && !line.isEmpty()) {
                        if (paragraph.length() != 0) {
                            paragraph.append('\n');
                        }
                        paragraph.append(line);
                        line = in.readLine();
                    }
                    if (paragraph.length() > 0) {
                        Converter converted = new Converter(paragraph);
                        out.write(converted.convert());
                        out.newLine();
                    }
                }
            } catch (IOException e) {
                System.err.println("IO error: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input-file not found 404: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        } finally {
        }
    }
}
