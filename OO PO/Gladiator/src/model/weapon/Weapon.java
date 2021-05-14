package model.weapon;

import model.utils.Utils;

public abstract class Weapon {
    public static double DEFAULT_DAMAGE = 10, DEFAULT_INTEGRITY = 1, DEFAILT_INTEGRITY_COEF = 0.1, DEFAULT_MISS_CHANCE = 0.05;

    public double damage;
    public double integrity ;
    public double integrityCoef;
    public double missChance;

    public double calcDamage() {
        double damage = Utils.round(this.damage * integrity);
        integrity = Utils.round(integrity - integrityCoef);
        return damage;
    }

    public final boolean missed() {
        return Math.random() < missChance;
    }

    public Weapon(double damage, double integrity, double integrityCoef, double missChance) {
        this.damage = damage;
        this.integrity = integrity;
        this.integrityCoef = integrityCoef;
        this.missChance = missChance;
    }
}
