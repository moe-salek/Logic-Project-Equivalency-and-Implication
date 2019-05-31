package tools;

import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Save {

    public static void save(String fileName, String content) {
        try {
            PrintWriter out = new PrintWriter(fileName);
            out.append(LocalDateTime.now().toString()).append(":").append('\n');
            out.append(content).append('\n');
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
