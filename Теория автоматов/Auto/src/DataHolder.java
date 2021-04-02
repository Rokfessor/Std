import java.util.ArrayList;

public class DataHolder {
    private final ArrayList<String> SDNF, SDNFU;

    public DataHolder(ArrayList<String> SDNF, ArrayList<String> SDNFU){
        this.SDNF = SDNF;
        this.SDNFU = SDNFU;
    }
    public ArrayList<String> getSDNF() {
        return SDNF;
    }

    public ArrayList<String> getSDNFU() {
        return SDNFU;
    }
}
