package Sorting;

public class Counter {
    public long replacements = 0;
    public long comparisons = 0;
    public long time = 0;
    public String name = "Sort by";

    @Override
    public String toString(){
        return name + ": " + time + " ms, " + comparisons + " comparisons, " + replacements + " replacements.";
    }
}
