public class Iteration {
    private int indexItem;
    private boolean[] condition;
    private String description;
    public Iteration(int N){
        condition = new boolean[N];
        for(int i = 0; i < N; i++){
            condition[i] = false;
        }
    }
    public void setConditionIndex(int index, boolean condition){
        this.condition[index] = condition;
    }
    public int getIndex() {
        return indexItem;
    }

    public void setIndex(int tryIndex) {
        this.indexItem = tryIndex;
    }

    public boolean[] getCondition() {
        return condition;
    }

    public void setCondition(boolean[] condition) {
        this.condition = condition;
    }


}

