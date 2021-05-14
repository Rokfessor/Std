package model.actions;

import model.gladiator.Gladiator;

public abstract class Debuff {
    public int count;
    public boolean first = true;

    public Debuff(int count) {
        this.count = count;
    }

    public boolean isActive() {
        return count <= 0;
    }

    abstract public Action onActionStart(int actionType);

    abstract public Action onActionEnd(int actionType);
}
