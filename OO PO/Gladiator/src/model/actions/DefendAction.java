package model.actions;

import model.gladiator.Gladiator;

public abstract class DefendAction  {
    public abstract void doAction(Gladiator me, Gladiator enemy, int ATTACK_TYPE, double damage);
}
