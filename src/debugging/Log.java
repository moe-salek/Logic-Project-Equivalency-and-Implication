package debugging;

import java.io.*;

public class Log {

    private static String file = "logs.txt";
    private static int line = 1;
    private static PrintWriter out;
    private static String contents;

    public static void init() {
        try {
            out = new PrintWriter(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void write_head(String head) {
        try {
            out.append(String.valueOf(line)).append(". ").append(head).append(": ").append("\n");
            ++line;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void write_info(String info) {
        try {
            out.append("   ").append(info).append("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void finish() {
        out.close();
        line = 1;
    }

    public static String getLogs() {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            contents = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }

}
