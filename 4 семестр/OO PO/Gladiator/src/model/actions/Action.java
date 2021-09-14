package model.actions;

import model.gladiator.Gladiator;

public abstract class Action {
    public static final int ATTACK = 0, SUPER_ATTACK = 1, BLOCK = 2, GET_ATTACK = 3, TIME = 4;
    public abstract void doAction(Gladiator me, Gladiator enemy);
}
