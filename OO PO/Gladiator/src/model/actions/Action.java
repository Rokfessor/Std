package model.actions;

import model.gladiator.Gladiator;

public abstract class Action {
    public abstract void doAction(Gladiator me, Gladiator enemy);
}
