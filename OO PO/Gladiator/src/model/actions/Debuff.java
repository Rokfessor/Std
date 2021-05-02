package model.actions;

import model.gladiator.Gladiator;

public abstract class Debuff {
    private boolean active;
    public boolean isActive(){return active;}
    abstract public Action onActionStart(Gladiator gladiator, int actionType);
    abstract public Action onActionEnd(Gladiator gladiator, int actionType);
}
