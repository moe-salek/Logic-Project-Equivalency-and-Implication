package debugging;

import java.io.PrintWriter;

public class Log {

    private static String file = "Logs.txt";
    private static int line = 1;
    private static PrintWriter out;

    public static void init() {
        try {
            out = new PrintWriter(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void write_head(String head) {
        out.append("\n");
        try {
            out.append(String.valueOf(line)).append(". ").append(head).append(": ").append("\n");
            ++line;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void write_info(String info) {
        try {
            out.append("\t").append(info).append("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void finish() {
        out.close();
        line = 1;
    }

}
