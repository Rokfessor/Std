package model.actions;

import model.gladiator.Gladiator;

public abstract class Attack {
    public final static int WEAPON = 0, ARM = 1;
    public final static int HEAD_ATTACK = 10, BODY_ATTACK = 11, LEG_ATTACK = 12;

    public abstract double doAttack(Gladiator enemy, int ATTACK_TYPE, int ATTACK_POINT);
}
