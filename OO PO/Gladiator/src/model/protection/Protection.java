package model.protection;

import model.actions.DefendAction;
import model.gladiator.Gladiator;
import model.utils.Utils;

public abstract class Protection {
    public static double DEFAULT_DEFEND = 1, DEFAULT_INTEGRITY = 1;
    double defend;
    public double integrity;
    public DefendAction action;

    public final double defend(Gladiator me, Gladiator enemy, double damage, int ATTACK_TYPE) {
        if (integrity != 0) {
            damage = Utils.round(defend * damage);
            defend = Utils.round(defend - integrity);
            if (integrity < 0)
                integrity = 0;
        }

        if (action != null)
            action.doAction(me, enemy, ATTACK_TYPE, damage);

        return damage;
    }

    public Protection(double defend, double integrity) {
        this.defend = defend;
        this.integrity = integrity;
    }
}
