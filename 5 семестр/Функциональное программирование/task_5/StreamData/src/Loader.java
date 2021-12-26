import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Loader {
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    public static List<Person> loadData(String path) {
        try {
            String[] data = Files.readString(Paths.get(path)).split("\n");

            List<Person> result = new ArrayList<>();

            Arrays.stream(data).forEach(
                    s -> {
                        try {
                            String[] vals = s.split(",");
                            Person person = new Person();

                            person.surname = deComm(vals[0]);
                            person.name = deComm(vals[1]);
                            person.secondname = deComm(vals[2]);
                            person.gender = deComm(vals[3]);
                            person.date = formatter.parse(deComm(vals[4]));
                            person.age = Integer.parseInt(deComm(vals[5]));
                            person.phoneNumber = deComm(vals[6]);
                            person.login = deComm(vals[7]);
                            person.password = deComm(vals[8]);
                            person.email = deComm(vals[9]);
                            result.add(person);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
            );

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static String deComm(String val) {
        return val.substring(1, val.length() - 1);
    }
}
