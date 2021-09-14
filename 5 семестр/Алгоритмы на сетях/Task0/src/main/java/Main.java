import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            IncidenceList list = Parser.parse(new File("src/main/resources/list.txt"));
            System.err.println(list.toString());
            list.

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
