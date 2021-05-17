package model.actions;

public abstract class Debuff {
    public int count;
    public boolean first = true;
    public double chance;

    public Debuff(int count, double chance) {
        this.count = count;
        this.chance = chance;
    }

    public boolean isActive() {
        return count <= 0;
    }

    abstract public Action onActionStart(int actionType);

    abstract public Action onActionEnd(int actionType);

    public final boolean isEjected() {
        return Math.random() < chance;
    }
}
