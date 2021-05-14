package model.actions;

import model.gladiator.Gladiator;

public abstract class Block {
    public abstract double doBlock(Gladiator me, Gladiator enemy, double damage, int ATTACK_POINT);
}
