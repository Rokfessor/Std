import java.util.ArrayList;
import java.util.List;

public class DataHolder {
    public Obj[] result;
    public List<Step> steps = new ArrayList<>();
    public long step;

    public DataHolder(){}

    public DataHolder(Obj[] result, List<Step> steps, long step) {
        this.step = step;
        this.result = result;
        this.steps = steps;
    }
}
