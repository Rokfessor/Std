package model.weapon;

import model.gladiator.Gladiator;

public abstract class Weapon {
    public double damage;
    public double integrity;
    public double integrityCoeff = 0.1;

    abstract void hit(Gladiator gladiator);
}
