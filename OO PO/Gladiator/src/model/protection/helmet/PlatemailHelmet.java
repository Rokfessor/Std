package model.protection.helmet;

import model.actions.Action;
import model.actions.DefendAction;
import model.gladiator.Gladiator;
import model.protection.Protection;
import model.utils.Utils;

public class PlatemailHelmet extends Protection {
    public PlatemailHelmet() {
        super(0.6,0.02);

        action = new DefendAction() {
            @Override
            public void doAction(Gladiator me, Gladiator enemy, int ATTACK_TYPE, double damage) {
                me.weapon.damage = Utils.round(me.weapon.damage + 0.02);
            }
        };
    }
}
