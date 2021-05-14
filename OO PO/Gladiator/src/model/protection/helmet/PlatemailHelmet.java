package model.protection.helmet;

import model.actions.Action;
import model.gladiator.Gladiator;
import model.protection.Protection;
import model.utils.Utils;

public class PlatemailHelmet extends Protection {
    public PlatemailHelmet() {
        super(0.6,1,0.05);

        action = new Action() {
            @Override
            public void doAction(Gladiator me, Gladiator enemy) {
                me.weapon.damage = Utils.round(me.weapon.damage + 0.02);
            }
        };
    }


    @Override
    public double defend(Gladiator me, Gladiator enemy, double damage) {
        damage = Utils.round((1 - integrity) * damage);
        integrity = Utils.round(integrity - integrityCoef);
        if (integrity < 0)
            integrity = 0;
        return damage;
    }
}
