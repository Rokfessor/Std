public class Step {
    String tryI;
    String[] N;
    int sumV;
    int sumCost;
    int vozmCost;
    int optCost;
    String note;

    public Step(String tryI, String[] n, int sumV, int sumCost, int vozmCost, int optCost, String note) {
        this.tryI = tryI;
        N = n;
        this.sumV = sumV;
        this.sumCost = sumCost;
        this.vozmCost = vozmCost;
        this.optCost = optCost;
        this.note = note;
    }
}
