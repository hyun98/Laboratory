package Chapter3_lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAroundPattern {
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("./data.txt"))) {
            return p.process(br);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}
