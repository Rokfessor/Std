package model;

public class Obj {
    private String name;
    private int weight;
    private int cost;

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }

    public Obj(String name, int weight, int cost) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
    }
}
