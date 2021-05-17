package model.protection.greaves;

import model.actions.DefendAction;
import model.gladiator.Gladiator;
import model.protection.Protection;
import model.utils.Utils;

public class PlatemailGreaves extends Protection {
    public PlatemailGreaves() {
        super(0.3, 0.02);

        action = new DefendAction() {
            @Override
            public void doAction(Gladiator me, Gladiator enemy, int ATTACK_TYPE, double damage) {
                me.weapon.damage = Utils.round(me.weapon.damage + 0.04);
            }
        };
    }
}
