package model.protection.armor;

import model.actions.Action;
import model.gladiator.Gladiator;
import model.protection.Protection;
import model.utils.Utils;

public class PlatemailArmor extends Protection {
    public PlatemailArmor() {
        super(1,1,1);

        action = new Action() {
            @Override
            public void doAction(Gladiator me, Gladiator enemy) {
                enemy.getDamage(3);
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
