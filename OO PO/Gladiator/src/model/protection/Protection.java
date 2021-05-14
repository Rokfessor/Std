package model.protection;

import model.actions.Action;
import model.gladiator.Gladiator;

public abstract class Protection {
    public static double DEFAULT_DEFEND = 1, DEFAULT_INTEGRITY = 1, DEFAILT_INTEGRITY_COEF = 0.1;
    double defend;
    public double integrity;
    public double integrityCoef;
    public Action action;

    public abstract double defend(Gladiator me, Gladiator enemy, double damage);

    public Protection(double defend, double integrity, double integrityCoef) {
        this.defend = defend;
        this.integrity = integrity;
        this.integrityCoef = integrityCoef;
    }
}
