package model.protection.greaves;

import model.actions.Action;
import model.gladiator.Gladiator;
import model.protection.Protection;
import model.utils.Utils;

public class PlatemailGreaves extends Protection {
    public PlatemailGreaves() {
        super(0.3,1,0.05);

        action = new Action() {
            @Override
            public void doAction(Gladiator me, Gladiator enemy) {
                me.weapon.damage = Utils.round(me.weapon.damage + 0.04);
            }
        };
    }

    @Override
    public double defend(Gladiator me, Gladiator enemy, double damage) {
        if (integrity != 0) {
            damage = Utils.round(integrity * damage);
            integrity = Utils.round(integrity - integrityCoef);
            if (integrity < 0)
                integrity = 0;
        }
        return damage;
    }
}
