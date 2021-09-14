import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static IncidenceList parse(File file) throws IOException {
        String[] incList = Files.readString(file.getAbsoluteFile().toPath()).split("\\r?\\n");
        System.err.println(Arrays.toString(incList));
        IncidenceList res = new IncidenceList();

        for (String inc : incList) {
            List<Integer> vals = new ArrayList<>();
            for (String e : inc.split("-"))
                vals.add(Integer.parseInt(e));
            res.addNew(vals.get(0));
            for (int i = 1; i < vals.size(); i++)
                res.addTo(vals.get(0), vals.get(i));
        }

        return res;
    }
}
