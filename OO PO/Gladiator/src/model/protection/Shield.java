package model.protection;

import model.gladiator.Gladiator;
import model.utils.Utils;

public class Shield extends Protection {


    public Shield() {
        super(DEFAULT_DEFEND, DEFAULT_INTEGRITY, DEFAILT_INTEGRITY_COEF);
    }

    public final boolean blocked() {
        return Math.random() < 0.3;
    }

    @Override
    public double defend(Gladiator me, Gladiator enemy, double damage) {
        damage = Utils.round(damage * defend);
        defend = Utils.round(defend - integrity);
        integrityCoef = Utils.round(integrity - integrityCoef);

        return damage;
    }
}
