package model.protection;

import javafx.scene.image.Image;
import model.actions.DefendAction;
import model.gladiator.Gladiator;
import model.utils.Utils;

public abstract class Protection {
    public static double DEFAULT_DEFEND = 1, DEFAULT_INTEGRITY = 0.1;
    public double defend;
    public double maxDefend;
    public double integrity;
    public DefendAction action;
    public Image image;

    public final double defend(Gladiator me, Gladiator enemy, double damage, int ATTACK_TYPE) {
        if (integrity != 0) {
            damage = Utils.round((1 - defend) * damage);
            defend = Utils.round(defend - integrity);
            if (defend < 0)
                defend = 0;
        }

        if (action != null)
            action.doAction(me, enemy, ATTACK_TYPE, damage);

        return damage;
    }

    public Protection(double defend, double integrity) {
        this.defend = this.maxDefend = defend;
        this.integrity = integrity;
    }

    @Override
    public String toString() {
        return "Protection{" +
                "defend=" + defend +
                ", maxDefend=" + maxDefend +
                ", integrity=" + integrity +
                '}';
    }
}
